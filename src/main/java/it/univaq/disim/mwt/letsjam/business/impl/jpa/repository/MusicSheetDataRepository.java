package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;


@Repository
public interface MusicSheetDataRepository extends MongoRepository<MusicSheetData, String> {
    

}
