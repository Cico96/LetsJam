package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

    @Query("SELECT c FROM Conversation c WHERE c.sender = :utente OR c.receiver = :utente")
    List<Conversation> findConversationBySender(@Param("utente") User user);


}