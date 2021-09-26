package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class ProfileController {
	
    @Autowired
    UserService utenteService;

	@Autowired
	private MusicSheetService spartitoService;
    
	@GetMapping("/profilo")
	public String getProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		List<MusicSheet> myMusicSheets = spartitoService.searchMusicSheetsByUserUsername(loggedUser.getUsername());
		System.out.println(loggedUser.getAvatar());
		model.addAttribute("myMusicSheets", myMusicSheets);
		model.addAttribute("profilo", loggedUser);
		
		return "profile/profile";
	}
	@GetMapping("/modifica-profilo")
	public String getModificaProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		List<MusicSheet> myMusicSheets = spartitoService.searchMusicSheetsByUserUsername(loggedUser.getUsername());
		model.addAttribute("profilo", loggedUser);

		return "profile/ModifyProfile";
	}
	@PostMapping("/modifica-profilo")
	public String ModificaProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();


		return "profile/profile";
	}

}
