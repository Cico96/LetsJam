package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.univaq.disim.mwt.letsjam.domain.User;
import org.hibernate.Session;
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
		return utenteRepository.findUserById(id);
		}
	
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		return utenteRepository.findUserByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) throws BusinessException {
		return utenteRepository.findUserByEmail(email);
	}

	@Override
	public boolean existsUserByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUserByUsername(username);
	}

	@Override
	public boolean existsUserByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUserByEmail(email);
	}


	@Override
	public User insert(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) throws BusinessException {
		utenteRepository.deleteById(id);
		
	}

	@Override
	public boolean UserIsAdmin(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(User user) throws BusinessException {
		User user_old = em.find(User.class, user.getId());
		user_old.setUsername(user.getUsername());
		user_old.setFirstname(user.getFirstname());
		user_old.setLastname(user.getLastname());
		user_old.setEmail(user.getEmail());
		user_old.setPreferredGenres(user.getPreferredGenres());
		System.out.println(user.getAvatar());
		if (user.getAvatar() != "") {
			user_old.setAvatar(user.getAvatar());
		} else {
			user_old.setAvatar(null);
		}
		em.detach(user_old);
		em.merge(user_old);
	}

	@Override
	public void like(User user, MusicSheet musicSheet) throws BusinessException {
		// TODO Auto-generated method stub
		Set<MusicSheet> spartiti = user.getLikedMusicSheets();
		spartiti.add(musicSheet);
		user.setLikedMusicSheets(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void dislike(User user, MusicSheet musicSheet) throws BusinessException {
		// TODO Auto-generated method stub
		Set<MusicSheet> spartiti = user.getLikedMusicSheets();
		spartiti.remove(musicSheet);
		user.setLikedMusicSheets(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void remove(User user, Genre genere) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Genre> preferredGenres = user.getPreferredGenres();
		preferredGenres.add(genere);
		user.setPreferredGenres(preferredGenres);
		utenteRepository.save(user);
	}


	@Override
	public List<User> getAllUsers() throws BusinessException {
		return utenteRepository.findAll();
	}

	@Override
	public User addUser(User user) throws BusinessException {
		utenteRepository.save(user);
		return user;
	}	

}
