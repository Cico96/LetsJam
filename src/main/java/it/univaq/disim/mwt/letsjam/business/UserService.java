package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface UserService {
	
	User findUserById(Long id) throws BusinessException;

	List<User> getAllUsers() throws BusinessException;
	
	User findUserByUsername(String username) throws BusinessException;
	
	User findUserByEmail(String email) throws BusinessException;

	boolean existsUserByUsername(String username) throws BusinessException;
	
	boolean existsUserByEmail(String email) throws BusinessException;
	
	User insert(User user) throws BusinessException;
	
	void deleteUserById(Long id) throws BusinessException;
	
	boolean UserIsAdmin(Long id) throws BusinessException;
	
	void update(User user) throws BusinessException;
	
	void like (User user, MusicSheet musicSheet) throws BusinessException;
	
	void dislike(User user, MusicSheet musicSheet) throws BusinessException;
	
	//void addGenerePreferito(Utente utente, Genere genere) throws BusinessException;
	
	void remove(User user, Genre genere) throws BusinessException;
	
	User addUser(User user) throws BusinessException;
}
