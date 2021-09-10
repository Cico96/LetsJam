package it.univaq.disim.mwt.letsjam.presentation;

import java.security.Principal;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import it.univaq.disim.mwt.letsjam.business.*;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.GenreRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SongRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Iterator;
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
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private SongRepository songRepository;
	
    @GetMapping("/home")
    public String home(Model model, Principal principal){
        //Logged
        List<MusicSheet> mostpopular = spartitoService.getMostPopularMusicSheets();
        if(principal !=null){

            //User a = ((CustomUserDetails) principal).getUser();
            //
            //System.out.println(a.getFirstname());

            List<MusicSheet> latest = spartitoService.getLastInsertMusicSheets();
            model.addAttribute("mostpopular", mostpopular);
            model.addAttribute("lastInsert", latest);
            return "home/homeLogged";
        }
//        Genre genere = genereService.findGenreByName("Fracchicco");
//        Song song = new Song();
//        song.setGenre(genere);
//        song.setAuthor("Fabri Fibra");
//        song.setTitle("Venerdi 17");
//        lyricsService.setLyrics(song);
//        spotifyService.setSongInfo(song);
//        songService.updateSong(song);
//        String autore = "Fabri Fibra";
//        Page<Song> songs = songRepository.searchSongsByAuthor(autore, PageRequest.of(0,5));
//        System.out.println(songs.getTotalElements());
//        System.out.println(songs.toList().iterator().next().getAuthor() + " " + songs.toList().iterator().next().getTitle());
//        Boolean verified = true;
//        Page<MusicSheet> ms = spartitoRepository.searchMusicSheetsByVerified(verified, PageRequest.of(0,5));
//        Iterator<MusicSheet> it = ms.toList().iterator();
//        while(it.hasNext()){
//            System.out.println(it.next().getTitle());
//        }
        String username = "antonioang";
        List<MusicSheet> ms = spartitoService.searchMusicSheetsByUserUsername(username);
        Iterator<MusicSheet> it = ms.iterator();
        while (it.hasNext()){
            MusicSheet spartito = it.next();
            System.out.println(spartito.getTitle() + " - " + spartito.getUser().getUsername());
        }
//        String album = "Mr. Simpatia";
//        Page<Song> songs = songRepository.searchSongsByAlbum(album, PageRequest.of(0,5));
//        System.out.println(songs.toList().iterator().next().getAlbumName() + " " + songs.toList().iterator().next().getAuthor());
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
