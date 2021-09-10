package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.*;
import it.univaq.disim.mwt.letsjam.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UserService userService;
    @Autowired
    private UserRepository utenteRepository;
	@Autowired
	private GenreService genereService;
	@Autowired
	private MusicSheetService spartitoService;
    @Autowired
	private MusicSheetRepository spartitoRepository;
	@Autowired
	private CommentService commentoService;
	@Autowired
    private SpotifyApiService spotifyService;
    @Autowired
    private SongService songService;
    @Autowired
    private Environment env;
    @Autowired
    private LyricsService lyricsService;
	
    @GetMapping("/home")
    public String home(Model model, Authentication authentication){
        
        List<MusicSheet> mostpopular = spartitoService.getMostPopularMusicSheets();
        if(authentication !=null){
            //Logged
            User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            System.out.println(loggedUser.getFirstname()+" "+loggedUser.getLastname());


            List<MusicSheet> latest = spartitoService.getLastInsertMusicSheets();
            model.addAttribute("mostpopular", mostpopular);
            model.addAttribute("lastInsert", latest);
            return "home/homeLogged";
        }
        //Not Logged

        //Genre genere = genereService.findGenreById((long)1);
        
        /*Genre genere = new Genre();
        genere.setName("Fracchicco");
        genere.setDescription("Sfranzi");
        genereService.addGenre(genere);
        System.out.println(genere.getName());*/

        /*Song song = new Song();
        song.setAuthor("Salmo");
        song.setTitle("Ho paura di uscire");
        lyricsService.setLyrics(song);
        spotifyService.setSongInfo(song);
        System.out.println(song.getAuthor() + " - " +song.getTitle());
        //song.setGenre(genere);
        songService.updateSong(song);*/

        model.addAttribute("mostpopular", mostpopular);
        return "home/home";
    }
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }
    //prova 
}
