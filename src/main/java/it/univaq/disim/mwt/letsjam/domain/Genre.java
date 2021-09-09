package it.univaq.disim.mwt.letsjam.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "generi")
@NoArgsConstructor
@Getter
@Setter
public class Genre extends AbstractPersistableEntity {
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 50, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String name;

	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 250, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String description;


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name) &&
            	Objects.equals(description, genre.description);
    }

	@Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
