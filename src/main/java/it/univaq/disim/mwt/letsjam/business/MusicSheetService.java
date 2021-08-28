package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface MusicSheetService {
	
	MusicSheet addSpartito(MusicSheet spartito) throws BusinessException;
	
	MusicSheetData getSpartitoData(Long id) throws BusinessException;

	MusicSheet findSpartitoById(Long id) throws BusinessException;
	
	MusicSheet findSpartitoByTitolo(String titolo) throws BusinessException;
	
//	Spartito findSpartitoByContenuto (String contenuto) throws BusinessException;
	
	MusicSheet findSpartitoByVerificato(String titolo) throws BusinessException;
	
	//void addLike(Spartito spartito, Long id_utente) throws BusinessException;
	
	void update(MusicSheet spartito) throws BusinessException;
	
	void deleteSpartitoById (Long id) throws BusinessException;
	
	MusicSheet insert(MusicSheet spartito) throws BusinessException;
	
	List<MusicSheet> getMostPopularMusicSheets() throws BusinessException;

	List<MusicSheet> getLastInsertMusicSheets() throws BusinessException;
}
