package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.Message;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    
    Optional<Message> findById(String id);
}
