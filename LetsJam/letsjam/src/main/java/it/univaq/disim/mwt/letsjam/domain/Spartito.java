package it.univaq.disim.mwt.letsjam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spartito {
	
	private Long id_spartito;
	private String autore;
	private String titolo;
	private String tipo;
	private String contenuto;
	
	private Utente utente;
	//upload spartito?siamo sicuri che sia "1 a n" e non "n a n"?
	//relazione spartito-brano:
	//un brano ha n spartiti, uno/n sparito hanno sempre lo stesso brano
}
