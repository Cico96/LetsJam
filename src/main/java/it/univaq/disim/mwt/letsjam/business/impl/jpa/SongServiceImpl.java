package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SongRepository;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	private SongRepository branoRepository;
	@PersistenceContext
	private EntityManager em;

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

	@Override
	public List<Song> searchSongsByAuthor(String author) throws BusinessException {
		Page<Song> songs = branoRepository.searchSongsByAuthor(author, PageRequest.of(0,5));
		return songs.toList();
	}

	@Override
	public List<Song> searchSongsByTitle(String title) throws BusinessException {
		Page<Song> songs = branoRepository.searchSongsByTitle(title, PageRequest.of(0,5));
		return songs.toList();
	}

	@Override
	public List<Song> searchSongsByAlbum(String album) throws BusinessException {
		Page<Song> songs = branoRepository.searchSongsByAlbum(album, PageRequest.of(0,5));
		return songs.toList();
	}

	@Override
	public List<Song> searchSongsByGenre(String name) throws BusinessException {
		Page<Song> songs = branoRepository.searchSongsByGenre(name, PageRequest.of(0,5));
		return songs.toList();
	}

	@Override
	public List<Song> getAllSong() throws BusinessException {
		return branoRepository.findAll();
	}

	@Override
	public List<Song> getProxyList(List<String> genres, String albumType, String orderBy, Boolean isExplicit, Boolean lyrics) throws BusinessException {
		String q = "SELECT s FROM Song s  ";
		if(! genres.isEmpty()){
			q += "WHERE s.genre.name in :generes ";
		}

		if(albumType != null && ! genres.isEmpty()){
			q += " AND s.albumType = :albumType ";
		}else if (albumType != null && genres.isEmpty()){
			q += "WHERE s.albumType = :albumType ";
		}

		if(isExplicit != null && (albumType != null || ! genres.isEmpty())){
			q += " AND s.isExplicit = :explicit ";
		}else if(isExplicit != null && (albumType != null || genres.isEmpty())){
			q += "WHERE s.isExplicit = :explicit ";
		}

		if (orderBy != null){
			q += " ORDER BY s." + orderBy;
		}
		System.out.println(q);

		List<Song> songs = em.createQuery(q)
				.setParameter("generes", genres)
				.setParameter("albumType", albumType)
				.setParameter("explicit", isExplicit)
				.getResultList();

		System.out.println(songs.size());
		return songs;

	}

}
