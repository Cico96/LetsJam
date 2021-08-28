package it.univaq.disim.mwt.letsjam.domain;

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
@Table(name = "brani")
@NoArgsConstructor
@Getter
@Setter
public class Song extends AbstractPersistableEntity{

	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String author;

	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 200, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String title;
	
	@OneToOne
	private Genre genre;
}
