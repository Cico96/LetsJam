package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

public interface SongService {
	
	Song findSongById(Long id) throws BusinessException;
	
	Song findSongByTitol(String titol) throws BusinessException;
	
	Song findSongByAuthor(String author) throws BusinessException;

	Song updateSong(Song song) throws BusinessException;
}
