package it.univaq.disim.mwt.letsjam.business;

import java.util.Optional;

import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface UtenteService {
	
	Utente findUtenteById(Long id) throws BusinessException;
	
	Optional<Utente> findUtenteByUsername(String username) throws BusinessException;
	
	boolean existsUtenteByUsername(String username) throws BusinessException;
	
	boolean existsUtenteByEmail(String email) throws BusinessException;
	
	Utente insert(Utente utente) throws BusinessException;
	
	void deleteUtenteById(Long id) throws BusinessException;
	
	boolean UtenteIsAdmin(Long id) throws BusinessException;
	
	void update(Utente utente, Genere genere) throws BusinessException;
	
	//void addGenerePreferito(Long id, String nome_genere) throws BusinessException;
	
}
