package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

	Instrument findStrumentoById(Long id);
	
	Instrument findStrumentoByNome(String nome);
}
