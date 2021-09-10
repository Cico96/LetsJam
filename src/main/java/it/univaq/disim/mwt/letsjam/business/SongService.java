package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface SongService {
	
	Song findSongById(Long id) throws BusinessException;
	
	Song findSongByTitol(String titol) throws BusinessException;
	
	Song findSongByAuthor(String author) throws BusinessException;

	Song updateSong(Song song) throws BusinessException;

	List<Song> getAllSong() throws BusinessException;
}
