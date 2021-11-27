package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.config.WebSecurityConfig;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;

import it.univaq.disim.mwt.letsjam.presentation.viewModels.SongSearchViewModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class ProfileController {

    @Autowired
    UserService utenteService;

    @Autowired
	GenreService genreService;

    @Autowired
	InstrumentService instrumentService;

	@Autowired
	private MusicSheetService spartitoService;

	@Autowired
	private UserService userService;



	private final Path root = Paths.get("uploads");
    
	@GetMapping("/profilo")
	public String getProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		List<MusicSheet> myMusicSheets = spartitoService.searchMusicSheetsByUserUsername(loggedUser.getUsername());

		model.addAttribute("myMusicSheets", myMusicSheets);
		model.addAttribute("profilo", loggedUser);
		return "profile/profile";
	}


	@GetMapping("/modifica-profilo")
	public String getModificaProfilo(Model model, Authentication authentication) throws BusinessException {
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		model.addAttribute("strumenti", instrumentService.getAllInstruments());
		model.addAttribute("generi", genreService.getAllGenres());
		System.out.println("uee "+loggedUser.getAvatar());
		model.addAttribute("profilo", loggedUser);
		return "profile/ModifyProfile";
	}

	@PostMapping("/modifica-profilo")
	public String ModificaProfilo(@RequestParam("imgInput") MultipartFile file, @ModelAttribute User profilo, Model model, Authentication authentication) throws BusinessException {
		utenteService.update(profilo);
		User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
		loggedUser.setUsername(profilo.getUsername());
		loggedUser.setFirstname(profilo.getFirstname());
		loggedUser.setLastname(profilo.getLastname());
		loggedUser.setEmail(profilo.getEmail());
		loggedUser.setPreferredGenres(profilo.getPreferredGenres());
		loggedUser.setPreferredInstruments(profilo.getPreferredInstruments());
		
		String fileExtension = (file.getOriginalFilename().split("\\."))[1];
		String fileName = String.valueOf(Objects.hash(loggedUser.getEmail(), loggedUser.getId()))+"."+fileExtension;
		try {
			Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("Impossibile salvare il file sul disco");
			e.printStackTrace();
		}
		loggedUser.setAvatar("/"+root.toString()+"/"+fileName);
		utenteService.update(loggedUser);

		List<MusicSheet> myMusicSheets = spartitoService.searchMusicSheetsByUserUsername(loggedUser.getUsername());
		model.addAttribute("myMusicSheets", myMusicSheets);
		model.addAttribute("profilo", loggedUser);
		return "profile/profile";
	}

}
