package it.univaq.disim.mwt.letsjam.business.impl.jpa.repository;

import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.univaq.disim.mwt.letsjam.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

	Genre findGenreById(Long id);

	Genre findGenreByName(String nome);

	@Query( value="SELECT g FROM Genre g ORDER BY rand()")
	Page<Genre> getRandomGenres(Pageable pageable);

}
