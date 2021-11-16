package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository utenteRepository;

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public User findUserById(Long id) throws BusinessException {
		try {
			return utenteRepository.findUserById(id);	
		} catch (Exception e) {
			throw new BusinessException("Utente non trovato \n"+e.getMessage());
		}
	}
	
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		try {
			return utenteRepository.findUserByUsername(username);
		} catch (Exception e) {
			throw new BusinessException("Utente non trovato \n"+e.getMessage());
		}
	}

	@Override
	public User findUserByEmail(String email) throws BusinessException {
		try {
			return utenteRepository.findUserByEmail(email);	
		} catch (Exception e) {
			throw new BusinessException("Utente non trovato \n"+e.getMessage());
		}
	}

	@Override
	public boolean existsUserByUsername(String username) throws BusinessException {
		try {
			return utenteRepository.existsUserByUsername(username);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());		
		}
	}

	@Override
	public boolean existsUserByEmail(String email) throws BusinessException {
		try {
			return utenteRepository.existsUserByEmail(email);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void deleteUserById(Long id) throws BusinessException {
		try {
			utenteRepository.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException("Errore durante la rimozione dell'utente \n"+e.getMessage());
		}
	}

	@Override
	public void update(User user) throws BusinessException {
		try {
			User user_old = em.find(User.class, user.getId());
			user_old.setUsername(user.getUsername());
			user_old.setFirstname(user.getFirstname());
			user_old.setLastname(user.getLastname());
			user_old.setEmail(user.getEmail());
			user_old.setPreferredGenres(user.getPreferredGenres());
			user_old.setPreferredInstruments(user.getPreferredInstruments());
			System.out.println(user.getAvatar());
			if (user.getAvatar() != "") {
				user_old.setAvatar(user.getAvatar());
			} else {
				user_old.setAvatar(null);
			}
			em.detach(user_old);
			em.merge(user_old);
		} catch (Exception e) {
			throw new BusinessException("Errore durante l'aggiornamento dell'utente \n"+e.getMessage());
		}
	}

	@Override
	public void like(User user, MusicSheet musicSheet) throws BusinessException {
		Set<MusicSheet> spartiti = user.getLikedMusicSheets();
		spartiti.add(musicSheet);
		user.setLikedMusicSheets(spartiti);
		try {
			utenteRepository.save(user);	
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void dislike(User user, MusicSheet musicSheet) throws BusinessException {
		Set<MusicSheet> spartiti = user.getLikedMusicSheets();
		spartiti.remove(musicSheet);
		user.setLikedMusicSheets(spartiti);
		try {
			utenteRepository.save(user);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void removePreferredGenre(User user, Genre genere) throws BusinessException {
		Set<Genre> preferredGenres = user.getPreferredGenres();
		preferredGenres.add(genere);
		user.setPreferredGenres(preferredGenres);
		try {
			utenteRepository.save(user);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void promoteToAdmin(Long id) {
		try {
			utenteRepository.promoteToAdmin(id,"amministratore");
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}


	@Override
	public List<User> getAllUsers() throws BusinessException {
		try {
			return utenteRepository.findAll();	
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public User addUser(User user) throws BusinessException {
		try {
			return utenteRepository.save(user);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

}
