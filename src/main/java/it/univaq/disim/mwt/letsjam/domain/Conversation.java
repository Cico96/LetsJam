package it.univaq.disim.mwt.letsjam.domain;

import groovy.transform.ToString;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

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

    private int conversationId; 
    
    private User sender;
    
    private User receiver;

    private Set<Message> messages;

}
