package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.Genere;

@Repository
public interface GenereRepository extends JpaRepository<Genere,Long> {
	
	Genere findGenereById(Long id);
	
	Genere findGenereByNome(String nome);

}
