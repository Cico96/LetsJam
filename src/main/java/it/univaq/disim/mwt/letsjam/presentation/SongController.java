package it.univaq.disim.mwt.letsjam.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.Song;

@Controller
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongService songService;
    
    @Autowired
    private MusicSheetService musicSheetService;

    @GetMapping("/{id}")
    public String single(Model model, @PathVariable Long id){
        Song song = songService.findSongById(id);
        List<MusicSheet> spartiti = musicSheetService.getMusicSheetsBySong(song);
        model.addAttribute("musicSheets", spartiti);
        model.addAttribute("song", song);
        return "song/song";
    }

}