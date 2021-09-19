package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.*;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.GenreRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SongRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
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
	private InstrumentService strumentoService;
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
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ScoreAnalyzerService scoreAnalyzerService;

    @GetMapping("/home")
    public String home(Model model, Authentication authentication){
        List<MusicSheet> mostpopular = spartitoService.getMostPopularMusicSheets();
        List<Genre> randomGenres = genereService.getRandomGenres();
        List<List<MusicSheet>> musicSheetByGenre = new ArrayList<>();
        for (Genre genere: randomGenres ) {
            musicSheetByGenre.add(spartitoService.getMusicSheetsByGenre(genere));
        }

        if(authentication !=null){
            //Logged
            User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            System.out.println(loggedUser.getFirstname()+" "+loggedUser.getLastname());

            List<MusicSheet> latest = spartitoService.getLastInsertMusicSheets();
            model.addAttribute("mostpopular", mostpopular);
            model.addAttribute("lastInsert", latest);
            model.addAttribute("musicSheetByGenre", musicSheetByGenre);

            return "home/homeLogged";
        }

        /*
        //ScoreAnalyzer test
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("testData/test2.musicxml").getFile());
        JSONObject json = scoreAnalyzerService.readScore(file);
        */

        List<String> strumenti = List.of("Violin","Clarinet");
        JSONObject res = scoreAnalyzerService.makeEmptyScore(strumenti);
        System.out.println(res.toString());

        model.addAttribute("mostpopular", mostpopular);
        model.addAttribute("musicSheetByGenre", musicSheetByGenre);
        return "home/home";
    }
	
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }
}
