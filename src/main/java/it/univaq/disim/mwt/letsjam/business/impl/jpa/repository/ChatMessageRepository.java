package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
