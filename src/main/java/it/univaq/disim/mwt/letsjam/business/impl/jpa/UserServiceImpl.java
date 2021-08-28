package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Set;

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
	
	@Override
	public User findUserById(Long id) throws BusinessException {
		return utenteRepository.findUtenteById(id);
		}
	
	@Override
	public User findUserByUsername(String username) throws BusinessException {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public User findUserByEmail(String email) throws BusinessException {
		return utenteRepository.findByEmail(email);
	}

	@Override
	public boolean existsUserByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByUsername(username);
	}

	@Override
	public boolean existsUserByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByEmail(email);
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
	public void update(User user, Genre genere) throws BusinessException {
		user.getPreferredGenres().add(genere);
		utenteRepository.save(user);
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
	public User addUser(User user) throws BusinessException {
		utenteRepository.save(user);
		return user;
	}	

}
