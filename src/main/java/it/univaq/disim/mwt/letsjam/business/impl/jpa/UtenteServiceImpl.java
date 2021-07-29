package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.UtenteService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public Optional<Utente> findUtenteById(Long id) throws BusinessException {
		return utenteRepository.findById(id);
	}
	
	@Override
	public Optional<Utente> findUtenteByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.findByUsername(username);
	}

	@Override
	public boolean existsUtenteByUsername(String username) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByUsername(username);
	}

	@Override
	public boolean existsUtenteByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return utenteRepository.existsUtenteByUsername(email);
	}


	@Override
	public Utente insert(Utente utente) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUtenteById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean UtenteIsAdmin(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Utente utente) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	

}
