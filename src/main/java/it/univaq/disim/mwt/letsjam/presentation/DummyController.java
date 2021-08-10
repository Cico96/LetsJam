package it.univaq.disim.mwt.letsjam.presentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.univaq.disim.mwt.letsjam.business.GenereService;
import it.univaq.disim.mwt.letsjam.business.SpartitoService;
import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.SpartitoData;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Controller
@RequestMapping("/")
public class DummyController {
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private GenereService genereService;
	@Autowired
	private SpartitoService spartitoService;
	
    @GetMapping("/")
    public String home(@RequestParam(name="id", required=false, defaultValue="13") Long id, Model model){
    	 Utente utente = utenteService.findUtenteById(id);
    	 Spartito spartito = new Spartito();
    	 SpartitoData sd = new SpartitoData();
    	 sd.setContent("vaffamocc");
    	 spartito.setTitolo("mannagg a bucchin");
    	 spartito.setVerificato(true);
    	 spartito.setData(sd);
    	 spartitoService.addSpartito(spartito);
    	 utenteService.like(utente, spartito);
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
