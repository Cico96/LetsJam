package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.SpartitoData;


@Repository
public interface SpartitoDataRepository extends MongoRepository<SpartitoData, String> {
    

}
