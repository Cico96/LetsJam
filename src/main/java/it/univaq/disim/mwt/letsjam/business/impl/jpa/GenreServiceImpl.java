package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import it.univaq.disim.mwt.letsjam.business.GenreService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.GenreRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
	@Autowired 
	private GenreRepository genereRepository;

	@Override
	public Genre insert(Genre genre) throws BusinessException {
		try {
			return genereRepository.save(genre);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Genre findGenreById(Long id) throws BusinessException {
		try {
			return genereRepository.findGenreById(id);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Genre findGenreByName(String name) throws BusinessException {
		try {
			return genereRepository.findGenreByName(name);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
	@Override
	public List<Genre> getRandomGenres() throws BusinessException {
		try {
			return genereRepository.getRandomGenres(PageRequest.of(0, 3)).toList();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Genre addGenre(Genre genre) throws BusinessException{
		try {
			return genereRepository.save(genre);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Genre> getAllGenres() throws BusinessException {
		try {
			return genereRepository.findAll();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
