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
@Document(collection = "conversation")
@ToString
public class Conversation {

    @Id
    private String id;

    private User receiver;

    private User sender;

    private Set<ChatMessage> chatMessages;

}