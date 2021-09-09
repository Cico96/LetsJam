package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.domain.User;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

import org.springframework.ui.Model;

@Controller
public class ProfileController {
	
    @Autowired
    UserService utenteService;
    
	@GetMapping("/profilo")
	public String getProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		model.addAttribute("profilo", loggedUser);
		
		return "profilo/profilo";
	}

}
