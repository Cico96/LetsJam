package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Commento;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface CommentoService {
	
	void addCommento(Long id_spartito, Long id_utente) throws BusinessException;
	
	Commento findCommentoById(Long id) throws BusinessException;
	
	void addRisposta(Long id_spartito, Long id_utente, Long id_commento) throws BusinessException;
	
	void update(Commento commento) throws BusinessException;
	
	void save(Commento commento) throws BusinessException;
}
