package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

public interface SongService {
	
	Song findSongById(Long id) throws BusinessException;
	
	Song findSongByTitol(String titol) throws BusinessException;
	
	Song findSongByAuthor(String author) throws BusinessException;

	Song updateSong(Song song) throws BusinessException;

	List<Song> searchSongsByAuthor(String author) throws BusinessException;

	List<Song> searchSongsByTitle(String title) throws BusinessException;

	List<Song> searchSongsByAlbum(String album) throws BusinessException;

	List<Song> searchSongsByGenre(String name) throws BusinessException;


	List<Song> getAllSong() throws BusinessException;
}
