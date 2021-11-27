package it.univaq.disim.mwt.letsjam.domain;

import groovy.transform.ToString;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "conversations")
@ToString
public class Conversation {

    @Id
    private String id;

    @JsonIgnore
    private Long receiverId;
    
    @JsonIgnore
    private Long senderId;
    
    @Transient
    private User sender;
    
    @Transient
    private User receiver;

    private Set<Message> messages;

}
