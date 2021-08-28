package it.univaq.disim.mwt.letsjam.domain;

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

@Entity
@Table(name = "commenti")
@NoArgsConstructor
@Getter
@Setter
public class Comment extends AbstractPersistableEntity{
	
	@Size(min = 3, max = 500, groups = {OnCreate.class, OnUpdate.class, Default.class})
	@NotNull
	private String contenuto;
	
	@OneToOne
	private Comment commentoPadre;
	
	@OneToOne
	private MusicSheet spartito;
	
	@OneToOne
	private User user;
	
}
