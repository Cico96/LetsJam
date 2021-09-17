package it.univaq.disim.mwt.letsjam.presentation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.JsonObject;
import it.univaq.disim.mwt.letsjam.business.ScoreAnalyzerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.MusicSheetSearchViewModel;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/musicsheets")
public class MusicSheetController {
	
	@Autowired
	private MusicSheetService spartitoService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private InstrumentService instrumentService;
	@Autowired
	private ScoreAnalyzerService as;
	
	private static final int PAGE_SIZE = 5;

	@GetMapping("/")
	public String all(Model model){
		List<Genre> genres = genreService.getAllGenres();
		Page<MusicSheet> musicSheets = spartitoService.getAllMusicSheets(PageRequest.of(0, PAGE_SIZE));
		List<Instrument> instruments = instrumentService.getAllInstruments();
		MusicSheetSearchViewModel formData = new MusicSheetSearchViewModel();
		formData.setTotalPages(musicSheets.getTotalPages());
        formData.setPageNumber(0);
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}

	@PostMapping("/")
	public String search(MusicSheetSearchViewModel formData, Model model){
		List<Genre> genres = genreService.getAllGenres();
		List<Instrument> instruments = instrumentService.getAllInstruments();
		Page<MusicSheet> musicSheets = spartitoService.searchMusicSheets(
			formData.getSearch(), formData.getSortBy(), 
			formData.getSortDirection(), formData.getGenres(), 
			formData.getInstruments(), formData.getVerified(), 
			formData.getRearranged(), formData.getPageNumber(), PAGE_SIZE);
		formData.setTotalPages(musicSheets.getTotalPages());
		
		model.addAttribute("formData", formData);
		model.addAttribute("instruments", instruments);
		model.addAttribute("musicSheets", musicSheets);
		model.addAttribute("genres", genres);
		return "musicSheets/all";
	}
	
	@GetMapping("/{id}")
	public String getSpartitoSingolo(@PathVariable("id") long id, Model model){
		MusicSheet musicSheet = spartitoService.findMusicSheetById(id);
		model.addAttribute("musicSheet", musicSheet);
		return "musicSheets/musicsheet";
	}
	
	//@PostMapping("/spartito/crea")
	
	@GetMapping("/spartito/{id}/delete")
	public String deleteSpartito(@PathVariable("id") long id) {
		spartitoService.deleteMusicSheetById(id);
		
		return "home/home";
		
	}

	@GetMapping("/create")
	public String view(){
		return "create-upload/flat";
	}

	@PostMapping("/create")
	public String upload(@RequestParam("file") MultipartFile file){
		System.out.println(file.getOriginalFilename());
		File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
		try {
			file.transferTo(convFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = as.readScore(convFile);
		System.out.println(as.hasTablature(json));
		System.out.println(json.toString());
		return "create-upload/flat";
	}
	

}
