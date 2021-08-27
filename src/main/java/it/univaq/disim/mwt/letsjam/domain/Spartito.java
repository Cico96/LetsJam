package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "spartiti")
@NoArgsConstructor
@Getter
@Setter
public class Spartito extends AbstractPersistableEntity{
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 25, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String titolo;

	@NotNull
	private Boolean verificato;

	@JsonInclude()
	@Transient
	private SpartitoData data;
	
	@OneToOne
	private Brano brano;
	
	@OneToOne
	private Utente utente;
	
	@ManyToMany(mappedBy = "likedSpartiti", fetch = FetchType.EAGER)
	@Basic(fetch = FetchType.LAZY)
	private Set<Utente> likes = new HashSet<>();
	
	@OneToMany()
	private Set<Strumento> strumenti;
}

