package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.*;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;

@Controller
@RequestMapping("/")
public class GeneralController {
    @Autowired
    private MusicSheetService spartitoService;

	@Autowired
	private GenreService genereService;

    @GetMapping("home")
    public String home(Model model, Authentication authentication){
        List<MusicSheet> mostpopular = spartitoService.getMostPopularMusicSheets();
        List<Genre> randomGenres = genereService.getRandomGenres();
        List<List<MusicSheet>> musicSheetByGenre = new ArrayList<>();
        for (Genre genere: randomGenres ) {
            musicSheetByGenre.add(spartitoService.getMusicSheetsByGenre(genere));
        }

        if(authentication !=null){
            //Logged
            User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            System.out.println(loggedUser.getFirstname()+" "+loggedUser.getLastname() + " "+loggedUser.getRoles());

            List<MusicSheet> latest = spartitoService.getLastInsertMusicSheets();
            model.addAttribute("mostpopular", mostpopular);
            model.addAttribute("lastInsert", latest);
            model.addAttribute("musicSheetByGenre", musicSheetByGenre);

            return "home/homeLogged";
        }

        model.addAttribute("mostpopular", mostpopular);
        model.addAttribute("musicSheetByGenre", musicSheetByGenre);
        return "home/home";
    }

    @GetMapping("admin/home")
    public String adminHome(Model model, Authentication authentication){
        if(authentication !=null){
            //Logged
            User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
            return "home/adminPanel";
        }
        return "common/forbidden";
    }
	
    
    @GetMapping("forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }

    @GetMapping("about-us")
    public String aboutUs(Model model){
        return "aboutUs/aboutUs";
    }

    
}
