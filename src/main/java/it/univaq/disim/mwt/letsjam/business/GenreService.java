package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

public interface GenreService {

	Genre insert(Genre genre) throws BusinessException;
	
	Genre findGenreById(Long id) throws BusinessException;
	
	Genre findGenreByName(String name) throws BusinessException;

	List<Genre> getRandomGenres() throws BusinessException;
	
}
