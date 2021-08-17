package it.univaq.disim.mwt.letsjam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    UtenteService utenteService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utente utente = null;
        try{
            utente = utenteService.findUtenteByEmail(email);
        }
        catch(BusinessException e){
            e.printStackTrace();
        }
        
        return new CustomUserDetails(utente);
    }
    
}
