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
import it.univaq.disim.mwt.letsjam.security.UserRoles;
import org.springframework.security.core.Authentication;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    public String search(@RequestParam("musicSeetId") Long musicSeetId, MusicSheetSearchViewModel formData, Model model){
        spartitoService.verifyMusicSheet(musicSeetId);

        return "admin/adminVerifySheets";
    }

    @GetMapping("/manageUsers")
    public String allUsers(Model model, Authentication authentication){
        List<User> users = userService.getAllUsers();
        users.remove(((CustomUserDetails) authentication.getPrincipal()).getUser());

        model.addAttribute("users", users);
        return "admin/manageUsers";
    }

    @PostMapping("/manageUsers")
    public String promoteUsers(@RequestParam("userIds") ArrayList<String> userIds, Model model, Authentication authentication){
        userIds.forEach(uid -> {
            if (userService.findUserById(Long.parseLong(uid)).getRole().equals(UserRoles.UTENTE)) {
                userService.promoteToAdmin(Long.parseLong(uid));
            }
        });
        List<User> users = userService.getAllUsers();
        users.remove(((CustomUserDetails) authentication.getPrincipal()).getUser());
        model.addAttribute("users", users);
        return "admin/manageUsers";
    }

    @PostMapping("/deleteUser")
    public String deleteUsers(@RequestParam("userIds") ArrayList<String> userIds, Model model,Authentication authentication){
        userIds.forEach(uid -> {
            if (userService.findUserById(Long.parseLong(uid)).getRole().equals(UserRoles.UTENTE)) {
                userService.deleteUserById(Long.parseLong(uid));
            }
        });
        List<User> users = userService.getAllUsers();
        users.remove(((CustomUserDetails) authentication.getPrincipal()).getUser());
        model.addAttribute("users", users);
        return "admin/manageUsers";
    }
}
