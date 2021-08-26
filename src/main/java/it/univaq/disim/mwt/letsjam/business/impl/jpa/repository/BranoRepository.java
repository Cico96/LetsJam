package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Brano;

public interface BranoRepository extends JpaRepository<Brano, Long> {

    Brano findBranoById(Long id);

    Brano findBranoByTitolo(String titolo);

    Brano findBranoByAutore(String autore);

}
