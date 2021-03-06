package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import org.hibernate.type.StringNVarcharType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.univaq.disim.mwt.letsjam.domain.Song;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
	
	Song findSongById(Long id);
	
	Song findSongByTitle(String titolo);
	
	Song findSongByAuthor(String autore);

	@Query(value = "SELECT s FROM Song s WHERE s.title LIKE CONCAT('%',:search,'%') OR s.author LIKE CONCAT('%',:search,'%')")
	List<Song> searchSongsByTitleAndAuthor(@Param("search") String search);

	@Query(value = "SELECT s FROM Song s WHERE s.albumName = :album_name")
	Page<Song> searchSongsByAlbum(@Param("album_name")String albumName, Pageable pageable);

	@Query(value = "SELECT s FROM Song s JOIN Genre g ON s.genre.id = g.id WHERE g.name = :name")
	Page<Song> searchSongsByGenre(@Param("name") String name, Pageable pageable);

}
