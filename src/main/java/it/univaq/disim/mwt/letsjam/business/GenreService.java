package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

public interface GenreService {

	Genre insert(Genre genre) throws BusinessException;
	
	Genre findGenreById(Long id) throws BusinessException;
	
	Genre findGenreByName(String name) throws BusinessException;

	List<Genre> getRandomGenres() throws BusinessException;

	Genre addGenre(Genre genre) throws BusinessException;

	List<Genre> getAllGenres() throws BusinessException;
}
