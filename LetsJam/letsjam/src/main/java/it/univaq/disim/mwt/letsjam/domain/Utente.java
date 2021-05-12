package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Utente {
	
	private Long id_utente;
	private String username;
	private String password;
	private String genere_preferito;
	private Long avatar;
	private Boolean admin;
	
	private Set<Spartito> spartiti = new HashSet<>();
	//upload spartito?

}
