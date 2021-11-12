package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
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

	private String author;

	@NotNull
	private Boolean verified;

	@NotNull
	private Boolean visibility;

	@NotNull
	private Boolean rearranged = false;

	@NotNull
	private Boolean hasTablature = false;

	@Transient
	private MusicSheetData data;
	
	@OneToOne()
	private Song song;
	
	@OneToOne
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinTable(
		name = "spartiti_likes", 
		joinColumns = @JoinColumn(name = "music_sheet_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> likes = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinTable(
    name = "spartiti_strumenti", 
    joinColumns = @JoinColumn(name = "music_sheet_id"), 
    inverseJoinColumns = @JoinColumn(name = "instrument_id"))
	private Set<Instrument> instruments;
}

