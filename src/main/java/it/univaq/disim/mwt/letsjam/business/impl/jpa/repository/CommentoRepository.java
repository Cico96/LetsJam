package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Commento;

public interface CommentoRepository extends JpaRepository<Commento, Long> {

    Commento findCommentoById(Long id);

}
