package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import core.GLA;
import genius.Lyrics;
import it.univaq.disim.mwt.letsjam.domain.Song;

@Service
public class LyricsService {
    @Autowired
    private Environment env;

    private GLA genius;

    @PostConstruct
    public void init(){
        this.genius = new GLA(env.getProperty("genius.client_id"), env.getProperty("genius.access_token"));
    }

    public void setLyrics(Song song){
        List<Lyrics> testi = this.genius.search(song.getTitle()+" "+song.getAuthor());
        if(testi.size() > 0){
            song.setLyrics(testi.iterator().next().getText());
        }
    }
}
