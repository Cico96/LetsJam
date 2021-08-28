package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;
import java.util.Set;

public interface MusicSheetService {
	
	MusicSheet addMusicSheet(MusicSheet musicSheet) throws BusinessException;
	
	MusicSheetData getMusicSheetData(Long id) throws BusinessException;

	MusicSheet findMusicSheetById(Long id) throws BusinessException;
	
	MusicSheet findMusicSheetByTitol(String titol) throws BusinessException;
	
//	Spartito findMusicSheetByContent (String content) throws BusinessException;
	
	MusicSheet findMusicSheetVerified(String titol) throws BusinessException;
	
	//void addLike(Spartito spartito, Long id_utente) throws BusinessException;
	
	void update(MusicSheet musicSheet) throws BusinessException;
	
	void deleteMusicSheetById (Long id) throws BusinessException;
	
	MusicSheet insert(MusicSheet musicSheet) throws BusinessException;
	
	List<MusicSheet> getMostPopularMusicSheets() throws BusinessException;

	List<MusicSheet> getLastInsertMusicSheets() throws BusinessException;
}
