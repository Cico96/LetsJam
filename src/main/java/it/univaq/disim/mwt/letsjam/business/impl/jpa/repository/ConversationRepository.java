package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String>{

    @Query("{'$or':[{'sender': ?0}, {'receiver': ?0}] }")
    List<Conversation> findConversationBySender(User user);


}