package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {
	
	Genre findGenereById(Long id);
	
	Genre findGenereByNome(String nome);

}
