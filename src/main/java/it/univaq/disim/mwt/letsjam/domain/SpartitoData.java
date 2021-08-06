package it.univaq.disim.mwt.letsjam.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "spartiti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SpartitoData {
    
    @Id
    private String id;
    
    private String content;
}
