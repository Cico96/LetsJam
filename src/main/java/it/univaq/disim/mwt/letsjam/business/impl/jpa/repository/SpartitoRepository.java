package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.stereotype.Repository;
import it.univaq.disim.mwt.letsjam.domain.Spartito;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SpartitoRepository extends JpaRepository<Spartito, Long>{
    
    Optional<Spartito> findById(Long id);
    
}