package it.univaq.disim.mwt.letsjam.business;

import java.util.Optional;

import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface UtenteService {
	
	Utente findUtenteById(Long id) throws BusinessException;
	
	Utente findUtenteByUsername(String username) throws BusinessException;
	
	boolean existsUtenteByUsername(String username) throws BusinessException;
	
	boolean existsUtenteByEmail(String email) throws BusinessException;
	
	Utente insert(Utente utente) throws BusinessException;
	
	void deleteUtenteById(Long id) throws BusinessException;
	
	boolean UtenteIsAdmin(Long id) throws BusinessException;
	
	void update(Utente utente, Genere genere) throws BusinessException;
	
	void like (Utente utente, Spartito spartito) throws BusinessException;
	
	void dislike(Utente utente, Spartito spartito) throws BusinessException;
	
	//void addGenerePreferito(Utente utente, Genere genere) throws BusinessException;
	
	void remove(Utente utente, Genere genere) throws BusinessException;
	
	Utente addUtente(Utente utente) throws BusinessException;
}
