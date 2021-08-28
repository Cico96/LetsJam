package it.univaq.disim.mwt.letsjam.presentation;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import it.univaq.disim.mwt.letsjam.business.CommentService;
import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UserService utenteService;
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
	
    @GetMapping("/home")
    public String home(Model model, Principal principal){
        MusicSheet spartito = spartitoService.findMusicSheetById((long)5);
        model.addAttribute("spartito", spartito);
        model.addAttribute("name", "Chicco");
        
        List<MusicSheet> spartiti = spartitoService.getMostPopularMusicSheets();
        List<MusicSheet> new_spartiti = spartitoService.getLastInsertMusicSheets();

        model.addAttribute("mostpopular", spartiti);
        model.addAttribute("lastInsert", new_spartiti);

        if(principal !=null){
            //Utente utente = utenteService.findUtenteByUsername(principal.getName());
            //model.addAttribute("utente", utente);
        }
        return "home/home";
    }
	
    @GetMapping("/")
    public RedirectView homeRedirect(Model model){
        return new RedirectView("home");
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
