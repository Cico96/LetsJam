package it.univaq.disim.mwt.letsjam.presentation;

import java.security.Principal;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import it.univaq.disim.mwt.letsjam.business.*;
import it.univaq.disim.mwt.letsjam.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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
	
    @GetMapping("/home")
    public String home(Model model, Authentication authentication){

        List<MusicSheet> mostpopular = spartitoService.getMostPopularMusicSheets();
        List<Genre> randomGenres = genereService.getRandomGenres();
        List<List<MusicSheet>> musicSheetByGenre = new ArrayList<>();

        for (Genre genere: randomGenres ) {
            musicSheetByGenre.add(spartitoService.getMusicSheetsByGenre(genere));
        }

//        Iterator<MusicSheet> iterator = randomSheets.iterator();
//        System.out.println(iterator.next().getTitle());
//        System.out.println(iterator.next().getTitle());

        if(authentication !=null){
            //Logged
            User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            System.out.println(loggedUser.getFirstname()+" "+loggedUser.getLastname());


            List<MusicSheet> latest = spartitoService.getLastInsertMusicSheets();
            model.addAttribute("mostpopular", mostpopular);
            model.addAttribute("lastInsert", latest);
            model.addAttribute("musicSheetByGenre", musicSheetByGenre);

//            Genre genre = genereService.findGenreByName("Pop");

//            Instrument strumento = strumentoService.findInstrumentByNome("chitarra");
//            strumento.setName("flauto traverso");
//            strumento.setInstrumentKey("Do diesis minore settima+");
//            strumentoService.insert(strumento);
//            Set<Instrument> strumenti = new HashSet<>();
//            strumenti.add(strumento);
//
//            Song song = new Song();
//            song.setAuthor("Nomadi");
//            song.setTitle("Io vagabondo");
//            song.setGenre(genre);
//            lyricsService.setLyrics(song);
//            spotifyService.setSongInfo(song);
//            System.out.println(song.getAuthor() + " - " +song.getTitle());
//            songService.updateSong(song);
//
//            MusicSheet spartito = new MusicSheet();
//            spartito.setTitle("Io vagabondo");
//            spartito.setVerified(false);
//            spartito.setUser(UserUtility.getUtente());
//            spartito.setSong(song);
//            spartito.setInstruments(strumenti);

//            spartitoService.insert(spartito);

            return "home/homeLogged";
        }

        model.addAttribute("mostpopular", mostpopular);
        model.addAttribute("musicSheetByGenre", musicSheetByGenre);
        return "home/home";
    }

//	@GetMapping("/")
//    public String home(@RequestParam(name="contenuto", defaultValue="Commento prova")String contenuto, Model model){
//    	 Utente utente = new Utente();
//    	 utente.setUsername("Achille lu tost3");
//    	 Genere genere = new Genere();
//    	 genere.setNome("vaffanculo");
//    	 genereService.save(genere);
//    	 utenteService.update(utente, genere);
//         model.addAttribute("name", utente.getGeneriPreferiti().iterator().next().getNome());
		
		//Commento commento = new Commento();
		
//        return "home/home";
//    }
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }
    //prova 
}
