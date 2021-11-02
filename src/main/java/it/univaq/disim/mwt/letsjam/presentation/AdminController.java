package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.presentation.viewModels.MusicSheetSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MusicSheetService spartitoService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private InstrumentService instrumentService;
    @Autowired
    private UserService userService;

    private static final int PAGE_SIZE = 5;

    @GetMapping("/verifyMusicsheet")
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
        return "admin/adminVerifySheets";
    }


    @PostMapping("/verifyMusicsheet")
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
        return "admin/adminVerifySheets";
    }

    @GetMapping("/manageUsers")
    public String allUsers(Model model){
        List<User> users = userService.getAllUsers();

        model.addAttribute("users", users);
        return "admin/manageUsers";
    }
}
