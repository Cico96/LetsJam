package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	
	Optional<Utente> findById(Long id);
	
	Optional<Utente> findByUsername(String username);
	
	boolean existsUtenteByUsername(String username);
	
	boolean existsUtenteByEmail(String email);
	
	//void update(Utente utente, Genere genere);

}
