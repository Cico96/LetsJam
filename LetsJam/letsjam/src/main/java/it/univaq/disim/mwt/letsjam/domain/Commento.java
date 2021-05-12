package it.univaq.disim.mwt.letsjam.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Commento {
	
	private Long id;
	private String contenuto;
	private LocalDate data;
	private Commento commento_padre;
	
	private Spartito spartito;
	private Utente utente;

}
