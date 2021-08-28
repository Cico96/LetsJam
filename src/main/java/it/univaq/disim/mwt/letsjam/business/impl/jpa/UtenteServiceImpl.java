package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Set;

import javax.transaction.Transactional;

import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
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
	public void update(User user, Genere genere) throws BusinessException {
		user.getGeneriPreferiti().add(genere);
		utenteRepository.save(user);
	}

	@Override
	public void like(User user, Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Spartito> spartiti = user.getLikedSpartiti();
		spartiti.add(spartito);
		user.setLikedSpartiti(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void dislike(User user, Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Spartito> spartiti = user.getLikedSpartiti();
		spartiti.remove(spartito);
		user.setLikedSpartiti(spartiti);
		utenteRepository.save(user);
	}

	@Override
	public void remove(User user, Genere genere) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Genere> generi = user.getGeneriPreferiti();
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
