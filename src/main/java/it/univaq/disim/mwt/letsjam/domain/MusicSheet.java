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
public class MusicSheet extends AbstractPersistableEntity{
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 25, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String title;

	@NotNull
	private Boolean verified;

	@NotNull
	private Boolean rearranged = false;

	@JsonInclude()
	@Transient
	private MusicSheetData data;
	
	@OneToOne
	private Song song;
	
	@OneToOne
	private User user;
	
	@ManyToMany(mappedBy = "likedMusicSheets", fetch = FetchType.EAGER)
	@Basic(fetch = FetchType.LAZY)
	private Set<User> likes = new HashSet<>();
	
	@OneToMany()
	private Set<Instrument> instruments;
}

