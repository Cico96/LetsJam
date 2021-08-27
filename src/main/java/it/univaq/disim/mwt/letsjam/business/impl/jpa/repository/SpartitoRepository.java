package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.stereotype.Repository;
import it.univaq.disim.mwt.letsjam.domain.Spartito;

import java.util.Set;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface SpartitoRepository extends JpaRepository<Spartito, Long>{
    
    Spartito findSpartitoById(Long id);
    
	Spartito findSpartitoByTitolo(String titolo);
    
    Spartito findSpartitoByVerificato(String titolo);
    
    void deleteSpartitoById(Long id);
    

    @Query(value="SELECT s FROM Spartito s ORDER BY size(s.likes) DESC")
    Page<Spartito> getMostPopularMusicSheets(Pageable pageable);

}
