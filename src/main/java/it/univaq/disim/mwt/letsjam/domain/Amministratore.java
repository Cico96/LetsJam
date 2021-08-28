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
@Table(name = "amministratori")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("amministratore")
@NoArgsConstructor
public class Amministratore extends User {
    
    public Amministratore(Long id){
        super();
        this.setId(id);
    }

}
