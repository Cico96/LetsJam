package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Comment;

public interface CommentoRepository extends JpaRepository<Comment, Long> {
	
	Comment findCommentoById(Long id);

}
