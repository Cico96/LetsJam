package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface SpartitoService {
	
	Spartito findSpartitoById(Long id) throws BusinessException;
	
	Spartito findSpartitoByTitolo(String titolo) throws BusinessException;
	
	Spartito findSpartitoByContento (String contenuto) throws BusinessException;
	
	Spartito findSpartitoByVerificato(String titolo) throws BusinessException;
	
	//void addLike(Spartito spartito, Long id_utente) throws BusinessException;
	
	void update(Spartito spartito) throws BusinessException;
	
	void deleteSpartitoById (Long id) throws BusinessException;
	
	Spartito insert(Spartito spartito) throws BusinessException;
	
	void save(Spartito spartito) throws BusinessException;
	
	//metodo insert e update hanno bisogno di un id utente?
}
