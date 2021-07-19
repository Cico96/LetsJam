package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface UtenteService {
	
	Utente findUserById(Long id) throws BusinessException;
	
	Utente findUserByUsername(String username) throws BusinessException;
	
	boolean existsUserByUsername(String username) throws BusinessException;
	
	boolean existsUserByEmail(String email) throws BusinessException;
	
	void save (Utente utente) throws BusinessException;
	
	Utente insert(Utente utente) throws BusinessException;
	
	void deleteUserById(Long id) throws BusinessException;
	
	boolean userIsAdmin(Long id) throws BusinessException;
	
	void update(Utente utente) throws BusinessException;
	
}
