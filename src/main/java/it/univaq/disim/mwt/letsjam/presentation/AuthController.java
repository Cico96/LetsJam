package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Controller
public class AuthController {
    
    @Autowired
    UtenteService utenteService;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("utente", new Utente());
            return "auth/register";
    }

    @PostMapping("/register")
    public String registerSumbit(@ModelAttribute Utente utente, Model model){
        //Controllo che non esista già un utente con la stessa email
        if(utenteService.existsUtenteByEmail(utente.getEmail())){
            model.addAttribute("error", "Email già esistente, inserisci un nuovo indirizzo email");
            return "auth/register";
        }
        //Cripto la password
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        utente.setPassword(encoder.encode(utente.getPassword()));
        //Salvo l'utente appena creato
        utenteService.addUtente(utente);
        System.out.println(utente.getRuolo()+" "+utente.getFirstname()+" "+utente.getLastname()+": registrato con successo");
            return "auth/login";
    }
    
}
