package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.domain.User;

@Controller
public class AuthController {
    
    @Autowired
    UserService utenteService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("utente", new User());
            return "auth/register";
    }

    @PostMapping("/register")
    public String registerSumbit(@ModelAttribute User user, Model model){
        //Controllo che non esista già un utente con la stessa email
        if(utenteService.existsUserByEmail(user.getEmail())){
            model.addAttribute("error", "email");
            return "auth/register";
        }
        //Cripto la password
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //Salvo l'utente appena creato
        utenteService.addUser(user);
        System.out.println(user.getRuolo()+" "+ user.getFirstname()+" "+ user.getLastname()+": registrato con successo");
            return "auth/login";
    }
    
}
