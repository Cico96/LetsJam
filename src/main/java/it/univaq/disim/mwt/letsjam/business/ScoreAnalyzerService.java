package it.univaq.disim.mwt.letsjam.business;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;
import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.domain.Instrument;

@Service
public class ScoreAnalyzerService {
    
    private static final String[] availableInstruments = {
        "Piano",
        "Organ",
        "Violin",
        "Cello",
        "Contrabass",
        "BassAcoustic",
        "BassElectric",
        "Guitar",
        "Banjo",
        "Sax",
        "Trumpet",
        "Horn",
        "Trombone",
        "Tuba",
        "Flute",
        "Oboe",
        "Clarinet",
        "Drum"
    };

    public String readScore(File file){
        try {
            String fileContent = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            //JSONObject json = XML.toJSONObject(fileContent);
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode json = xmlMapper.readTree(file);
            
            /*JSONObject newSpartito = extractInstrumentPart(json, "P1");
            Pair<List<Instrument>, HashMap<String, String>> result= getInstruments(json);
            List<Instrument> instruments = result.getValue0();
            HashMap<String, String> instrumentPartMappings = result.getValue1();

            instruments.forEach(instrument -> System.out.println(instrument.getName()));
            instrumentPartMappings.forEach((key, value) -> System.out.println(key+" - "+value));
            System.out.println(getScoreTitle(json));
            System.out.println(getScoreAuthor(json));
            System.out.println(hasTablature(json));
            System.out.println(newSpartito.query("/score-partwise/part/0/id").toString());*/
            return json.toString();
        } catch (IOException e) { 
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String,String> getInstruments(JSONObject json){
        JSONArray parts = new JSONArray();
        JSONArray scoreInstruments = new JSONArray();
        HashMap<String,String> instrumentPartMappings = new HashMap<String,String>();

        parts.put(json.query("/score-partwise/part-list/score-part"));
        parts.forEach(item ->{
            JSONArray part = new JSONArray();
            if(item instanceof JSONObject){ part.put(item); }
            else{ part = (JSONArray) item; }
            part.forEach(partElement ->{
                scoreInstruments.put(((JSONObject)partElement).query("/score-instrument"));
            });
        });

        scoreInstruments.forEach(scoreInstrument ->{
            if(scoreInstrument != null){
                JSONArray scoreInstrumentJson = new JSONArray();
                if(scoreInstrument instanceof JSONObject){scoreInstrumentJson.put(scoreInstrument); }
                else{ scoreInstrumentJson = (JSONArray) scoreInstrument; }
                if(scoreInstrumentJson.length() > 0){
                    scoreInstrumentJson.forEach(el ->{
                        String name = ((JSONObject) el).getString("instrument-name");
                        String partId = ((JSONObject) el).getString("id");
                        for(int i = 0; i < availableInstruments.length; i++){
                            if(name.equals(availableInstruments[i]) || name.contains(availableInstruments[i])){
                                instrumentPartMappings.put(availableInstruments[i], partId.split("-")[0]);
                            }
                        }
                    });
                }
            }
        });

        return instrumentPartMappings;
    }

    public Set<Instrument> toInstrumentSet(Map<String, String> mappings){
        Set<Instrument> instruments = new HashSet<Instrument>();
        Set<String> instrumentNames = mappings.keySet();
        instrumentNames.forEach(name -> {
            Instrument i = new Instrument();
            i.setName(name);
            instruments.add(i);
        });
        return instruments;
    }

    public String getScoreTitle(JSONObject json){
        try {
            return json.query("/score-partwise/work/work-title").toString();   
        } catch (Exception e) {
            System.out.println("Titolo non trovato");
            return "";
        }
    }

    public String getScoreAuthor(JSONObject json){
        try {
            return json.query("/score-partwise/identification/creator/content").toString();   
        } catch (Exception e) {
            System.out.println("Autore non trovato");
            return "";
        }
    }

    public JSONObject extractInstrumentPart(JSONObject json, String id){
        JSONObject result = new JSONObject(json.toString());
        JSONArray parts = new JSONArray();
        if(result.query("/score-partwise/part") instanceof JSONObject){
            parts.put(result.query("/score-partwise/part"));
        }
        else{
            parts = (JSONArray) result.query("/score-partwise/part");
        }
        JSONArray partToExtract = new JSONArray();
        for(int i = 0; i < parts.length(); i++){
            String partId = parts.getJSONObject(i).query("/id").toString();
            if(partId.equals(id)) {
                partToExtract.put(parts.getJSONObject(i));  
            }
        }
        JSONObject scorePartwise = (JSONObject) result.query("/score-partwise");
        scorePartwise.remove("part");
        scorePartwise.put("part", partToExtract);
        return result;
    }

    public Boolean hasTablature(JSONObject json){
        return json.toString().indexOf("fret") >= 0;
    }

    public JSONObject makeEmptyScore(List<String> instrumentNames){
        JSONObject result = new JSONObject();
        JSONObject scorePartWise = new JSONObject();
        scorePartWise.put("version", "4.0");
        JSONObject partList = new JSONObject();
        JSONArray scoreParts = new JSONArray();
        JSONArray parts = new JSONArray();
        for (int i = 0; i < instrumentNames.size(); i++) {
            JSONObject scorePart = new JSONObject();
            scorePart.put("$id", "P"+(i+1));
            scorePart.put("part-name", instrumentNames.get(i));
            scoreParts.put(scorePart);

            JSONObject part = new JSONObject();
            part.put("$id", "P"+(i+1));
            JSONObject measure = new JSONObject();
            measure.put("number", "1");
            JSONObject attributes = new JSONObject();
            attributes.put("divisions", "1");
            JSONObject key = new JSONObject();
            key.put("fifths", "0");
            attributes.put("key", key);
            
            JSONObject time = new JSONObject();
            time.put("beats", "4");
            time.put("beat-type", "4");

            JSONObject clef = new JSONObject();
            clef.put("sign","G");
            clef.put("line", "2");

            JSONObject note = new JSONObject();
            JSONObject pitch = new JSONObject();
            pitch.put("step", "C");
            pitch.put("octave", "4");
            note.put("pitch", pitch);
            note.put("duration", "4");
            note.put("type", "whole");

            measure.put("attributes", attributes);
            measure.put("time", time);
            measure.put("clef", clef);
            measure.append("note", note);

            part.put("measure", new JSONArray().put(measure));
            parts.put(part);
        }

        partList.put("score-part", scoreParts);
        scorePartWise.put("part", parts);
        scorePartWise.put("part-list", partList);
        result.put("score-partwise", scorePartWise);
        
        return result;
    }

}
