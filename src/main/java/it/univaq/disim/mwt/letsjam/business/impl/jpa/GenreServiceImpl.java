package it.univaq.disim.mwt.letsjam.business.impl.jpa;

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
		return genereRepository.save(genre);
	}

	@Override
	public Genre findGenreById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return genereRepository.findGenreById(id);
	}

	@Override
	public Genre findGenreByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return genereRepository.findGenreByName(name);
	}
	@Override
	public List<Genre> getRandomGenres() throws BusinessException {
		return genereRepository.getRandomGenres(PageRequest.of(0, 3)).toList();
	}

	@Override
	public void addGenre(Genre genre) throws BusinessException{
		genereRepository.save(genre);
	}

}
