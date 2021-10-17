package it.univaq.disim.mwt.letsjam.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;

import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@Entity
@Table(name = "strumenti")
@NoArgsConstructor
@Getter
@Setter
public class Instrument extends AbstractPersistableEntity {
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String name;

	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String instrumentKey;

	@ManyToMany(mappedBy="instruments", fetch = FetchType.LAZY)
	private Set<MusicSheet> musicSheets;

}
