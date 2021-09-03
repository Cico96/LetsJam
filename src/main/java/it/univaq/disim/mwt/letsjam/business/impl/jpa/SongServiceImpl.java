package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SongRepository;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
@Service
public class SongServiceImpl implements SongService {
	@Autowired
	private SongRepository branoRepository;

	@Override
	public Song findSongById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findSongById(id);
	}

	@Override
	public Song findSongByTitol(String titol) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findSongByTitle(titol);
	}

	@Override
	public Song findSongByAuthor(String author) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findSongByAuthor(author);
	}
	@Override
	public Song updateSong(Song song) throws BusinessException{
		return branoRepository.save(song);
	}

}
