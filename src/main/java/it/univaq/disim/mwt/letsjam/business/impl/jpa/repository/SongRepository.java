package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
	
	Song findSongById(Long id);
	
	Song findSongByTitle(String titolo);
	
	Song findSongByAuthor(String autore);

}