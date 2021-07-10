package it.univaq.disim.mwt.letsjam.domain;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import it.univaq.disim.mwt.letsjam.presentation.validation.EmailUnique;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnCreate;
import it.univaq.disim.mwt.letsjam.presentation.validation.OnUpdate;
import it.univaq.disim.mwt.letsjam.presentation.validation.UsernameUnique;
import lombok.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
public class Utente extends AbstractPersistableEntity{
	
	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 25, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String firstname;

	@NotEmpty(groups ={OnCreate.class, Default.class})
	@Size(min = 3, max = 25, groups = {OnCreate.class, OnUpdate.class, Default.class})
	private String lastname;

	@UsernameUnique(groups = {OnCreate.class})
    @NotEmpty(groups = {OnCreate.class, Default.class})
    @Size(min = 3, max = 25, groups = {OnCreate.class, OnUpdate.class, Default.class})
    @Column(unique = true)
	private String username;


	@EmailUnique(groups = {OnCreate.class})
    @NotEmpty(groups = {OnCreate.class, Default.class})
    @Email(groups = {OnCreate.class, OnUpdate.class, Default.class})
    @Size(min = 3, max = 45, groups = {OnCreate.class, OnUpdate.class, Default.class})
    @Column(unique = true)
    private String email;
	
	@JsonIgnore
    @NotEmpty(groups = {OnCreate.class, Default.class})
    private String password;
	
	private String generePreferito;
	private Long avatar;
	
	// Da sostituire con il @DiscriminatorColumn(name = "role")
	private Boolean admin;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(firstname, utente.firstname) &&
                Objects.equals(lastname, utente.lastname) &&
                Objects.equals(email, utente.email) &&
                Objects.equals(username, utente.username) &&
                Objects.equals(password, utente.password);
    }

	@Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, username, password);
    }
}
