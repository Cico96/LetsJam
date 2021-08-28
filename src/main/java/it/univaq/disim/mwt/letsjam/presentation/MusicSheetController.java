package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Controller
public class MusicSheetController {
	
	@Autowired
	MusicSheetService spartitoService;
	
	@GetMapping("/spartitiList")
	public String getAllSpartiti() throws BusinessException {
		return "spartito/allSpartiti";
	}
	
	@GetMapping("/spartito/{id}")
	public String getSpartitoSingolo(@PathVariable("id") long id, Model model) throws BusinessException {
		
		MusicSheet spartito = spartitoService.findMusicSheetById(id);
		model.addAttribute("spartito", spartito);
		return "spartito/allSpartiti";
	}
	
	//@PostMapping("/spartito/crea")
	
	@GetMapping("/spartito/{id}/delete")
	public String deleteSpartito(@PathVariable("id") long id) {
		spartitoService.deleteMusicSheetById(id);
		
		return "home/home";
		
	}
	

}
