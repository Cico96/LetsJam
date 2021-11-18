package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.SongService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SongRepository;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	private SongRepository branoRepository;
	@PersistenceContext
	private EntityManager em;

	@Override
	public Song findSongById(Long id) throws BusinessException {
		try {
			return branoRepository.findSongById(id);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public Song findSongByTitle(String title) throws BusinessException {
		try {
			return branoRepository.findSongByTitle(title);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public Song findSongByAuthor(String author) throws BusinessException {
		try {
			return branoRepository.findSongByAuthor(author);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public Song updateSong(Song song) throws BusinessException{
		try {
			return branoRepository.save(song);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Song> searchSongsByTitleAndAuthor(String search) throws BusinessException {
		try {
			return branoRepository.searchSongsByTitleAndAuthor(search);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public List<Song> searchSongsByAlbum(String album) throws BusinessException {
		try {
			return branoRepository.searchSongsByAlbum(album, PageRequest.of(0,5)).toList();
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public List<Song> searchSongsByGenre(String name) throws BusinessException {
		try {
			return branoRepository.searchSongsByGenre(name, PageRequest.of(0,5)).toList();
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare il brano specificato \n"+e.getMessage());
		}
	}

	@Override
	public Page<Song> getAllSong(Pageable pageable) throws BusinessException {
		try {
			return branoRepository.findAll(pageable);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public Page<Song> getSearchedSongs(String search, List<String> genres, String albumType, String orderBy, Boolean isExplicit, Boolean hasLyrics, String sortDirection, int pageNumber, int pageSize) throws BusinessException {		
		String q = "SELECT s FROM Song s ";
		boolean whereClause = false;
		
		//SEARCH STRING
		if(search != null && search.length() > 0){
			q+="WHERE (s.title LIKE CONCAT('%',:search,'%') OR s.author LIKE CONCAT('%',:search,'%') OR s.albumName LIKE CONCAT('%',:search,'%')) ";
			whereClause = true;
		}
		//GENERES
		if(! genres.isEmpty() && !whereClause){
			q += "WHERE s.genre.name in :genres ";
			whereClause = true;
		}
		else if(! genres.isEmpty() && whereClause){
			q += "AND s.genre.name in :genres ";
		}
		//ALBUM TYPE
		if(albumType != null && !whereClause){
			q += "WHERE s.albumType = :albumType ";
			whereClause = true;
		}else if (albumType != null && whereClause){
			q += "AND s.albumType = :albumType ";
		}
		//EXPLICIT
		if(isExplicit != null && isExplicit && !whereClause){
			q += "WHERE s.isExplicit = :explicit ";
			whereClause = true;
		}else if(isExplicit != null && isExplicit && whereClause){
			q += "AND s.isExplicit = :explicit ";
		}
		//LYRICS
		if(hasLyrics != null && hasLyrics && !whereClause){
			q += "WHERE s.lyrics IS NOT NULL ";
			whereClause = true;
		}else if(hasLyrics != null && hasLyrics && whereClause){
			q += "AND s.lyrics IS NOT NULL ";
		}
		//ORDER BY
		if (orderBy != null){
			q += "ORDER BY s."+orderBy+" "+sortDirection;
		}

		Query query =  em.createQuery(q);
		if(search != null && search.length() > 0) query.setParameter("search", search);
		if(!genres.isEmpty()) query.setParameter("genres", genres);
		if(albumType != null) query.setParameter("albumType", albumType);
		if(isExplicit != null && isExplicit) query.setParameter("explicit", isExplicit);
		
		try {
			List<Song> songs = query.getResultList();
			int toIndex = (((pageNumber*pageSize)+pageSize) <= songs.size()) ? (pageNumber*pageSize)+pageSize : songs.size();
			return new PageImpl<Song>(songs.subList(pageNumber*pageSize, toIndex), PageRequest.of(pageNumber, pageSize), songs.size());
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}
}
