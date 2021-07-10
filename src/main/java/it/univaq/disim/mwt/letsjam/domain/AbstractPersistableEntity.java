package it.univaq.disim.mwt.letsjam.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Setter
@Getter
@MappedSuperclass
@ToString(exclude = {"createDateTime", "updateDateTime"})
public class AbstractPersistableEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @JsonIgnore
    private Long version;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}
