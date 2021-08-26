package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Strumento;

public interface StrumentoRepository extends JpaRepository<Strumento, Long> {

	Strumento findStrumentoById(Long id);
	
	Strumento findStrumentoByNome(String nome);
}
