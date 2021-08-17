package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public Utente findUtenteById(Long id) throws BusinessException {
		return utenteRepository.findById(id).get();
		}
	
	@Override
	public Utente findUtenteByUsername(String username) throws BusinessException {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public Utente findUtenteByEmail(String email) throws BusinessException {
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
	public Utente insert(Utente utente) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.save(utente);
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
	public void update(Utente utente, Genere genere) throws BusinessException {
		utente.getGeneriPreferiti().add(genere);
		utenteRepository.save(utente);
	}

	@Override
	public void like(Utente utente, Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Spartito> spartiti = utente.getLikedSpartiti();
		spartiti.add(spartito);
		utente.setLikedSpartiti(spartiti);
		utenteRepository.save(utente);
	}

	@Override
	public void dislike(Utente utente, Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Spartito> spartiti = utente.getLikedSpartiti();
		spartiti.remove(spartito);
		utente.setLikedSpartiti(spartiti);
		utenteRepository.save(utente);
	}

	@Override
	public void remove(Utente utente, Genere genere) throws BusinessException {
		// TODO Auto-generated method stub
		Set<Genere> generi = utente.getGeneriPreferiti();
		generi.add(genere);
		utente.setGeneriPreferiti(generi);
		utenteRepository.save(utente);
	}

	@Override
	public Utente addUtente(Utente utente) throws BusinessException {
		utenteRepository.save(utente);
		return utente;
	}	

}
