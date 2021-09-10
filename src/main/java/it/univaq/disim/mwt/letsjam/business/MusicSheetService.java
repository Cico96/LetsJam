package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface MusicSheetService {
	
	MusicSheet addMusicSheet(MusicSheet musicSheet) throws BusinessException;
	
	MusicSheetData getMusicSheetData(Long id) throws BusinessException;

	MusicSheet findMusicSheetById(Long id) throws BusinessException;
	
	MusicSheet findMusicSheetByTitle(String title) throws BusinessException;
	
//	Spartito findMusicSheetByContent (String content) throws BusinessException;
	
	MusicSheet findMusicSheetVerified(String title) throws BusinessException;
	
	//void addLike(Spartito spartito, Long id_utente) throws BusinessException;
	
	void update(MusicSheet musicSheet) throws BusinessException;
	
	void deleteMusicSheetById (Long id) throws BusinessException;
	
	MusicSheet insert(MusicSheet musicSheet) throws BusinessException;
	
	List<MusicSheet> getMostPopularMusicSheets() throws BusinessException;

	List<MusicSheet> getLastInsertMusicSheets() throws BusinessException;

	List<MusicSheet> getMusicSheetsBySong(Song song) throws BusinessException;

	List<MusicSheet> searchMusicSheetsByTitle(String title) throws BusinessException;

	List<MusicSheet> searchMusicSheetsByVerified(Boolean verified) throws BusinessException;

	List<MusicSheet> searchMusicSheetsByUserUsername(String username) throws BusinessException;

	List<MusicSheet> getMusicSheetsByGenre(Genre genre) throws BusinessException;
}
