package it.univaq.disim.mwt.letsjam.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
import it.univaq.disim.mwt.letsjam.security.RuoloUtente;
import lombok.*;

@Entity
@Table(name = "utenti")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("utente")
public class User extends AbstractPersistableEntity{
	
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
	
    @Lob
    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
	private Byte[] avatar;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinTable(
    name = "spartiti_likes", 
    joinColumns = @JoinColumn(name = "utente_id"), 
    inverseJoinColumns = @JoinColumn(name = "spartito_id"))
    private Set<MusicSheet> likedMusicSheets;
	
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinTable(
    name = "generi_preferiti", 
    joinColumns = @JoinColumn(name = "utente_id"), 
    inverseJoinColumns = @JoinColumn(name = "genere_id"))
    private Set<Genre> preferredGenres = new HashSet<>();

    
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

	@Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, username, password);
    }

    @Transient
    public RuoloUtente getRoles(){
        return RuoloUtente.valueOf(this.getClass().getAnnotation(DiscriminatorValue.class).value().toUpperCase());
        //return RuoloUtente.UTENTE;
    }
}


