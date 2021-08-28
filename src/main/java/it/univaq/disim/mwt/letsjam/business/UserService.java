package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface UserService {
	
	User findUtenteById(Long id) throws BusinessException;
	
	User findUtenteByUsername(String username) throws BusinessException;
	
	User findUtenteByEmail(String email) throws BusinessException;

	boolean existsUtenteByUsername(String username) throws BusinessException;
	
	boolean existsUtenteByEmail(String email) throws BusinessException;
	
	User insert(User user) throws BusinessException;
	
	void deleteUtenteById(Long id) throws BusinessException;
	
	boolean UtenteIsAdmin(Long id) throws BusinessException;
	
	void update(User user, Genre genere) throws BusinessException;
	
	void like (User user, MusicSheet spartito) throws BusinessException;
	
	void dislike(User user, MusicSheet spartito) throws BusinessException;
	
	//void addGenerePreferito(Utente utente, Genere genere) throws BusinessException;
	
	void remove(User user, Genre genere) throws BusinessException;
	
	User addUtente(User user) throws BusinessException;
}
