package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genere {
	
	private Long id;
	private String nome;
	private String descrizione;
	private Set<Genere> generiPreferiti = new HashSet<>();
	//bacco col colbacco
	//ai ai9aa
}
