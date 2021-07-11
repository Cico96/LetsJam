package it.univaq.disim.mwt.letsjam.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Brano {
	private Long id;
	private String autore;
	private String titolo;
	private Genere genere;
}
