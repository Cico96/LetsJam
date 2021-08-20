package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import org.springframework.ui.Model;

@Controller
public class ProfileController {
	
    @Autowired
    UtenteService utenteService;
    
	@GetMapping("/profilo")
	public String getProfilo(Model model) throws BusinessException {
		Utente utente = UserUtility.getUtente();
		model.addAttribute("profilo", utente);
		
		return "profilo/profilo";
	}

}
