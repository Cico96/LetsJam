package it.univaq.disim.mwt.letsjam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Utente {
	
	private Long id;
	private String username;
	private String password;
	private String generePreferito;
	private Long avatar;
	private Boolean admin;
	
//ohf
}
