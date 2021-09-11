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
import javax.persistence.Query;

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
	public List<Song> getSearchedSongs(String search, List<String> genres, String albumType, String orderBy, Boolean isExplicit, Boolean hasLyrics, String sortDirection) throws BusinessException {
		String q = "SELECT s FROM Song s ";
		boolean whereClause = false;

		if(search != null && search.length() > 0){
			q+="WHERE (s.title LIKE CONCAT('%',:search,'%') OR s.author LIKE CONCAT('%',:search,'%') OR s.albumName LIKE CONCAT('%',:search,'%')) ";
			whereClause = true;
		}
		
		if(! genres.isEmpty() && !whereClause){
			q += "WHERE s.genre.name in :genres ";
			whereClause = true;
		}
		else if(! genres.isEmpty() && whereClause){
			q += "AND s.genre.name in :genres ";
		}

		if(albumType != null && !whereClause){
			q += "WHERE s.albumType = :albumType ";
			whereClause = true;
		}else if (albumType != null && whereClause){
			q += "AND s.albumType = :albumType ";
		}

		if(isExplicit != null && isExplicit && !whereClause){
			q += "WHERE s.isExplicit = :explicit ";
			whereClause = true;
		}else if(isExplicit != null && isExplicit && whereClause){
			q += "AND s.isExplicit = :explicit ";
		}

		if(hasLyrics != null && hasLyrics && !whereClause){
			q += "WHERE s.lyrics IS NOT NULL ";
			whereClause = true;
		}else if(hasLyrics != null && hasLyrics && whereClause){
			q += "AND s.lyrics IS NOT NULL ";
		}

		if (orderBy != null){
			q += "ORDER BY s."+orderBy+" "+sortDirection;
		}
		System.out.println(q);

		Query query =  em.createQuery(q);
		if(search != null && search.length() > 0) query.setParameter("search", search);
		if(!genres.isEmpty()) query.setParameter("genres", genres);
		if(albumType != null) query.setParameter("albumType", albumType);
		if(isExplicit != null && isExplicit) query.setParameter("explicit", isExplicit);
		List<Song> songs = query.getResultList();

		System.out.println(songs.size());
		return songs;
	}

}
