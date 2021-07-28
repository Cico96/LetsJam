package it.univaq.disim.mwt.letsjam.presentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UtenteService utenteService;
	
    @GetMapping("/")
    public String home(@RequestParam(name="id", required=false, defaultValue="1") Long id, Model model){
    	 Optional<Utente> utente = utenteService.findUtenteById(id);
         model.addAttribute("name", utente.get().getFirstname());
        return "home/home";
    }
    
    @GetMapping("/error")
    public String error(Model model){
        return "common/error";
    }
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }

}
