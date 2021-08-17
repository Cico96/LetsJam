package it.univaq.disim.mwt.letsjam.presentation;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.letsjam.business.GenereService;
import it.univaq.disim.mwt.letsjam.business.SpartitoService;
import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.SpartitoData;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UtenteService utenteService;
    @Autowired
    private UtenteRepository utenteRepository;
	@Autowired
	private GenereService genereService;
	@Autowired
	private SpartitoService spartitoService;
	
    @GetMapping("/")
    public String home(Model model, Principal principal){
        model.addAttribute("name", "Chicco");
        if(principal !=null){
            Utente utente = utenteService.findUtenteByUsername(principal.getName());
            model.addAttribute("utente", utente);
            model.addAttribute("name", utente.getFirstname()+" "+utente.getLastname());
        }
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
