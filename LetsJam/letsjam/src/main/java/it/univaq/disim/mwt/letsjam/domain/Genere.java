package it.univaq.disim.mwt.letsjam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genere {
	
	private Long id_genere;
	private String nome;
	private String descrizione;
	private Set<Genere> generi_preferiti = new HashSet<>();

}
