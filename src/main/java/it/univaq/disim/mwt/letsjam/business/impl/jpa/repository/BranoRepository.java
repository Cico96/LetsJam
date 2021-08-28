package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Song;

public interface BranoRepository extends JpaRepository<Song, Long> {
	
	Song findBranoById(Long id);
	
	Song findBranoByTitolo(String titolo);
	
	Song findBranoByAutore(String autore);

}
