package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.stereotype.Repository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;

import java.util.Set;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface SpartitoRepository extends JpaRepository<MusicSheet, Long>{
    
    MusicSheet findSpartitoById(Long id);
    
	MusicSheet findSpartitoByTitolo(String titolo);
    
    MusicSheet findSpartitoByVerificato(String titolo);
    
    void deleteSpartitoById(Long id);
    

    @Query(value="SELECT s FROM MusicSheet s ORDER BY size(s.likes) DESC")
    Page<MusicSheet> getMostPopularMusicSheets(Pageable pageable);

    @Query(value = "SELECT s FROM MusicSheet s ORDER BY s.createDateTime DESC")
    Page<MusicSheet> getLastInsert(Pageable pageable);

}
