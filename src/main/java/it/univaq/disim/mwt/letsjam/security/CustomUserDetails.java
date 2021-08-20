package it.univaq.disim.mwt.letsjam.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.univaq.disim.mwt.letsjam.domain.Utente;

public class CustomUserDetails implements UserDetails{

    private final Utente utente;

    public CustomUserDetails(Utente utente){
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return utente.getRuolo().getAuthorities();
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId(){
        return utente.getId();
    }
    
	public Utente getUtente() {
		return utente;
	}

    @Override
    public String toString(){
        return "Dettagli Utente: "+utente.getFirstname()+" "+utente.getLastname()+""+utente.getEmail();
    }
    
}
