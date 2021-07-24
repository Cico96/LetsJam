package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	Optional<Utente> findById(Long id);
	
	boolean findUtenteByUsername(String username);
	
	boolean findUtenteByEmail(String email);

}
