package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.User;

@Repository
public interface UtenteRepository extends JpaRepository<User, Long> {
	
	User findUtenteById(Long id);
	
	User findByUsername(String username);

	User findByEmail(String email);
	
	boolean existsUtenteByUsername(String username);
	
	boolean existsUtenteByEmail(String email);
	
	void deleteUtenteById(Long id);

}
