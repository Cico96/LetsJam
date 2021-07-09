package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spartito {
	
	private Long id;
	private String titolo;
	private String tipo;
	private String contenuto;
	
	private Brano brano;
	private Utente utente;//inserimento
	private Set<Utente> likes = new HashSet<>();
	
}
