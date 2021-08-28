package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.SpartitoData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface SpartitoService {
	
	Spartito addSpartito(Spartito spartito) throws BusinessException;
	
	SpartitoData getSpartitoData(Long id) throws BusinessException;

	Spartito findSpartitoById(Long id) throws BusinessException;
	
	Spartito findSpartitoByTitolo(String titolo) throws BusinessException;
	
//	Spartito findSpartitoByContenuto (String contenuto) throws BusinessException;
	
	Spartito findSpartitoByVerificato(String titolo) throws BusinessException;
	
	//void addLike(Spartito spartito, Long id_utente) throws BusinessException;
	
	void update(Spartito spartito) throws BusinessException;
	
	void deleteSpartitoById (Long id) throws BusinessException;
	
	Spartito insert(Spartito spartito) throws BusinessException;
	
	List<Spartito> getMostPopularMusicSheets() throws BusinessException;

	List<Spartito> getLastInsertMusicSheets() throws BusinessException;
}
