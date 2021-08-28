package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface SongService {
	
	Song findSongById(Long id) throws BusinessException;
	
	Song findSongByTitol(String titol) throws BusinessException;
	
	Song findSongByAuthor(String author) throws BusinessException;
}
