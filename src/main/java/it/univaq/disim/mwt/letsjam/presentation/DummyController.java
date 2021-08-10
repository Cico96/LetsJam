package it.univaq.disim.mwt.letsjam.presentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.letsjam.business.GenereService;
import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private GenereService genereService;
	
    @GetMapping("/")
    public String home(@RequestParam(name="email", required=false, defaultValue="prova@prova.it") String email, Model model){
    	 Boolean utente = utenteService.existsUtenteByEmail(email);
         model.addAttribute("name", utente);
        return "home/home";
    }
	
//	@GetMapping("/")
//    public String home(@RequestParam(name="id", defaultValue="1") Long id, Model model){
//    	 Utente utente = new Utente();
//    	 utente.setUsername("Achille lu tost3");
//    	 Genere genere = new Genere();
//    	 genere.setNome("vaffanculo");
//    	 genereService.save(genere);
//    	 utenteService.update(utente, genere);
//         model.addAttribute("name", utente.getGeneriPreferiti().iterator().next().getNome());
//        return "home/home";
//    }
    
    @GetMapping("/error")
    public String error(Model model){
        return "common/error";
    }
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }
    
}
