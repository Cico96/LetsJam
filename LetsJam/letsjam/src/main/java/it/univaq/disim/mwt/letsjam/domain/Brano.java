package it.univaq.disim.mwt.letsjam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Brano {
	//Test testabile testato con la testa
	private Long id_brano;
	private String autore;
	private String titolo;
	private Genere genere;
}
