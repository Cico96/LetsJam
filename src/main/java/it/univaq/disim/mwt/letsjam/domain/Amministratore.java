package it.univaq.disim.mwt.letsjam.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "administrators")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("admin")
@NoArgsConstructor
public class Amministratore extends Utente{
    
    public Amministratore(Long id){
        super();
        this.setId(id);
    }

}
