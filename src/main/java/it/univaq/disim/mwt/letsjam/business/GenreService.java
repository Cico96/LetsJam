package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface GenreService {
	
	
	Genre findGenreById(Long id) throws BusinessException;
	
	Genre findGenreByName(String name) throws BusinessException;
	
	void addGenre(Genre genre) throws BusinessException;

	List<Genre> getAllGenres() throws BusinessException;
}
