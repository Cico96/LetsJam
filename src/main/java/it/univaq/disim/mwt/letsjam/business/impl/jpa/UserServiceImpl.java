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
	public User findUtenteById(Long id) throws BusinessException {
		return utenteRepository.findUtenteById(id);
		}
	
	@Override
	public User findUtenteByUsername(String username) throws BusinessException {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public User findUtenteByEmail(String email) throws BusinessException {
		return utenteRepository.findByEmail(email);
	}

	@Override
	public boolean existsUtenteByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByUsername(username);
	}

	@Override
	public boolean existsUtenteByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByEmail(email);
	}


	@Override
	public User insert(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.save(user);
	}

	@Override
	public void deleteUtenteById(Long id) throws BusinessException {
		utenteRepository.deleteById(id);
		
	}

	@Override
	public boolean UtenteIsAdmin(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(User user, Genre genere) throws BusinessException {
		user.getGeneriPreferiti().add(genere);
		utenteRepository.save(user);
	}

	@Override
	public void like(User user, MusicSheet spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<MusicSheet> spartiti = user.getLikedSpartiti();
		spartiti.add(spartito);
		user.setLikedSpartiti(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void dislike(User user, MusicSheet spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<MusicSheet> spartiti = user.getLikedSpartiti();
		spartiti.remove(spartito);
		user.setLikedSpartiti(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void remove(User user, Genre genere) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Genre> generi = user.getGeneriPreferiti();
		generi.add(genere);
		user.setGeneriPreferiti(generi);
		utenteRepository.save(user);
	}

	@Override
	public User addUtente(User user) throws BusinessException {
		utenteRepository.save(user);
		return user;
	}	

}
