package it.univaq.disim.mwt.letsjam.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.SongSearchViewModel;

@Controller
@RequestMapping("songs")
public class SongController {

    @Autowired
    private SongService songService;
    
    @Autowired
    private MusicSheetService musicSheetService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}")
    public String single(Model model, @PathVariable Long id){
        Song song = songService.findSongById(id);
        List<MusicSheet> spartiti = musicSheetService.getMusicSheetsBySong(song);
        model.addAttribute("musicSheets", spartiti);
        model.addAttribute("song", song);
        return "song/song";
    }

    @GetMapping("/")
    public String all(Model model){
        List<Genre> genres = genreService.getAllGenres();
        List<Song> songs = songService.getAllSong();
        SongSearchViewModel formData = new SongSearchViewModel();
        model.addAttribute("formData", formData);
        model.addAttribute("songs", songs);
        model.addAttribute("genres", genres);
        return "song/all";
    }

    @PostMapping("/")
    public String search(@ModelAttribute SongSearchViewModel formData, Model model){
        List<Genre> genres = genreService.getAllGenres();
        List<Song> songs = songService.getSearchedSongs(
            formData.getSearch(), formData.getGenres(),
            formData.getAlbumType(), formData.getSortBy(), 
            formData.getExplicit(), formData.getLyrics(), formData.getSortDirection());
        model.addAttribute("formData", formData);
        model.addAttribute("songs", songs);
        model.addAttribute("genres", genres);
        return "song/all";
    }

}
