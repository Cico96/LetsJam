package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class UtenteServiceImpl {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	public Optional<Utente> findById(Long id) throws BusinessException {
		return utenteRepository.findById(id);
	}
	
	public boolean findUtenteByUsername(String username) throws BusinessException {
		return utenteRepository.findUtenteByUsername(username);
	}
	
	public boolean findUtenteByEmail(String email) throws BusinessException {
		return utenteRepository.findUtenteByEmail(email);
	}

}
