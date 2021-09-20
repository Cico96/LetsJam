package it.univaq.disim.mwt.letsjam.presentation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import it.univaq.disim.mwt.letsjam.business.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.SpotifyApiService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;
import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.LyricsService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.MusicSheetSearchViewModel;
import org.springframework.web.multipart.MultipartFile;
import com.wrapper.spotify.model_objects.specification.Track;

@Controller
@RequestMapping("/musicsheets")
public class MusicSheetController {
	
	@Autowired
	private MusicSheetService spartitoService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private ScoreAnalyzerService as;
	@Autowired
    private SpotifyApiService spotifyService;
	@Autowired
    private LyricsService lyricsService;
	@Autowired
	private SongService songService;
	
	private static final int PAGE_SIZE = 5;

	@GetMapping("/")
	public String all(Model model){
		List<Genre> genres = genreService.getAllGenres();
		Page<MusicSheet> musicSheets = spartitoService.getAllMusicSheets(PageRequest.of(0, PAGE_SIZE));
		List<Instrument> instruments = instrumentService.getAllInstruments();
		MusicSheetSearchViewModel formData = new MusicSheetSearchViewModel();
		formData.setTotalPages(musicSheets.getTotalPages());
        formData.setPageNumber(0);
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}

	@PostMapping("/")
	public String search(MusicSheetSearchViewModel formData, Model model){
		List<Genre> genres = genreService.getAllGenres();
		List<Instrument> instruments = instrumentService.getAllInstruments();
		Page<MusicSheet> musicSheets = spartitoService.searchMusicSheets(
			formData.getSearch(), formData.getSortBy(), 
			formData.getSortDirection(), formData.getGenres(), 
			formData.getInstruments(), formData.getVerified(), 
			formData.getRearranged(), formData.getPageNumber(), PAGE_SIZE);
		formData.setTotalPages(musicSheets.getTotalPages());
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}
	
	@GetMapping("/{id}")
	public String getSpartitoSingolo(@PathVariable("id") long id, Model model){
		MusicSheet musicSheet = spartitoService.findMusicSheetById(id);
		model.addAttribute("musicSheet", musicSheet);
		return "musicSheets/musicsheet";
	}
	
	@GetMapping("/spartito/{id}/delete")
	public String deleteSpartito(@PathVariable("id") long id) {
		spartitoService.deleteMusicSheetById(id);
		
		return "home/home";
		
	}

	@GetMapping("/create")
	public String view(Model model){
		List<Instrument> instrumentList = instrumentService.getAllInstruments();
		model.addAttribute("instruments", instrumentList);
		return "create-upload/flat";
	}
	
	@PostMapping("/create")
	public String upload(@RequestParam("file") MultipartFile file,  Authentication authentication){
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		String extension = (file.getOriginalFilename() != null) ? file.getOriginalFilename().split("\\.")[1] : "";
		System.out.println(extension);
		if(extension.equals("musicxml")){
			File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
			try {
				file.transferTo(convFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*HashMap<String, String> mappings = as.getInstruments(json);
			Set<Instrument> strumenti = as.toInstrumentSet(mappings);
			strumenti.forEach(i -> System.out.println(i.getName()));
			
			MusicSheetData data = new MusicSheetData();
			data.setContent(json.toString());
			data.setInstrumentMapping(mappings);

			MusicSheet spartito = new MusicSheet();
			spartito.setData(data);
			spartito.setTitle(as.getScoreTitle(json)+" - "+as.getScoreAuthor(json));
			spartito.setUser(loggedUser);
			spartito.setInstruments(strumenti);
			spartito.setRearranged(false);
			spartito.setVerified(false);
			spartito.setHasTablature(as.hasTablature(json));*/

			/*Song song = new Song();
			song.setAuthor("Federico Berti");
			song.setTitle("Polka del Dirigibile");
			spotifyService.setSongInfo(song);
			songService.updateSong(song);
			
			spartito.setSong(song);*/

			//spartitoService.addMusicSheet(spartito);
		}
		return "create-upload/flat";
	}
	

	@PostMapping("/analyze")
	public ResponseEntity<String> analyze(@RequestParam("score") String score){
		if(score != null && score.length() > 0){
				String title = as.getScoreTitle(new JSONObject(score));
				String author = as.getScoreAuthor(new JSONObject(score));
				JSONObject result = new JSONObject();
				result.put("title", title);
				result.put("author", author);
				result.put("content", score);

				return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PostMapping("/getEmptyScore")
	public ResponseEntity<String> getEmptyScore(@RequestParam("instruments") String instrumentList){
		if(instrumentList != null && instrumentList.length() > 0){
				List<String> strumenti = (new JSONArray(instrumentList))
											.toList()
											.stream()
											.map(Object::toString)
											.collect(Collectors.toList());
				JSONObject result = as.makeEmptyScore(strumenti);
				return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}


	@GetMapping("/brani")
	public String searchSong(){
		JSONArray result = new JSONArray();
		String title = "Albachiara";
		String author = "Vasco Rossi";
		List<Song> dbSongs = songService.searchSongsByTitleAndAuthor(title, author);
		List<Track> spotifySongs = spotifyService.searchSong(title, author);

		dbSongs.forEach(s ->{
			spotifySongs.forEach(t ->{
				//Da finire
				if(s.getSpotifyId() != null && s.getSpotifyId().equals(t.getId())){
					JSONObject brano = new JSONObject();
					brano.put("title", s.getTitle());
					brano.put("author", s.getAuthor());
					brano.put("spotifyId", s.getSpotifyId());
					brano.put("id", s.getId());
					result.put(brano);
				}

			});
		});
		
		return "create-upload/flat";
	}
}
