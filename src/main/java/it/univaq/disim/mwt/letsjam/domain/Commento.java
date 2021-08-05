package it.univaq.disim.mwt.letsjam.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "commenti")
@NoArgsConstructor
@Getter
@Setter
public class Commento extends AbstractPersistableEntity{
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 500, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String contenuto;
	
	@OneToOne
	private Commento commentoPadre;
	
	@OneToOne
	private Spartito spartito;
	
	@OneToOne
	private Utente utente;
	
}
