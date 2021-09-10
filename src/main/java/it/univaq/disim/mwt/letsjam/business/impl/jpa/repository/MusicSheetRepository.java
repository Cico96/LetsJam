package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import org.springframework.stereotype.Repository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.Song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface MusicSheetRepository extends JpaRepository<MusicSheet, Long>{
    
    MusicSheet findMusicSheetById(Long id);
    
	MusicSheet findMusicSheetByTitle(String title);
    
    MusicSheet findMusicSheetByVerified(String title);
    
    void deleteMusicSheetById(Long id);
    
    @Query(value="SELECT s FROM MusicSheet s ORDER BY size(s.likes) DESC")
    Page<MusicSheet> getMostPopularMusicSheets(Pageable pageable);

    @Query(value = "SELECT s FROM MusicSheet s ORDER BY s.createDateTime DESC")
    Page<MusicSheet> getLastInsert(Pageable pageable);

    @Query(value="SELECT ms FROM MusicSheet ms WHERE ms.song = :song")
    Page<MusicSheet> getMusicSheetsBySong(@Param("song") Song song, Pageable pageable);

    @Query(value="SELECT ms FROM MusicSheet ms WHERE ms.song.genre = :genre")
    Page<MusicSheet> getMusicSheetsByGenre(@Param("genre") Genre genre, Pageable pageable);

}
