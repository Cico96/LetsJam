package it.univaq.disim.mwt.letsjam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "brani")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Song extends AbstractPersistableEntity{

	@NotNull
	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String author;

	@NotNull
	@Size(min = 3, max = 200, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String title;
	
	@Size(min = 3, max = 200, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String albumName;

	@Size(min = 3, max = 200, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String albumType;

	@Size(min = 3, max = 500, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String imageUrl;

	private Boolean isExplicit;

	private int duration;
	
	@Column(columnDefinition="TEXT")
	private String lyrics;

	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String spotifyId;

	@OneToOne
	private Genre genre;
}
