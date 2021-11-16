package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.Instrument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetDataRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MusicSheetServiceImpl implements MusicSheetService {

	@Autowired
	private MusicSheetRepository musicSheetRepository;

	@Autowired
	private MusicSheetDataRepository musicSheetDataRepository;

	@Autowired
	private InstrumentService instrumentService;

	@PersistenceContext
	private EntityManager em;

	@Override
	public MusicSheet findMusicSheetById(Long id) throws BusinessException {
		try {
			MusicSheet musicSheet = musicSheetRepository.findById(id).get();
			musicSheet.setData(musicSheetDataRepository.findById(id.toString()).get());
			return musicSheet;
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare lo spartito \n"+e.getMessage());
		}
	}

	@Override
	public MusicSheetData getMusicSheetData(Long id) throws BusinessException {
		try {
			return musicSheetDataRepository.findById(id.toString()).get();
		} catch (Exception e) {
			throw new BusinessException("Impossibile ottenere i dati dello spartito \n"+e.getMessage());
		}
	}

	@Override
	public MusicSheet addMusicSheet(MusicSheet musicSheet) throws BusinessException {
		Set<Instrument> strumenti = new HashSet<Instrument>();
		for (Instrument instrument : musicSheet.getInstruments()) {
			if(instrumentService.findInstrumentByName(instrument.getName()) == null) strumenti.add(instrumentService.addInstrument(instrument));
			else strumenti.add(instrumentService.findInstrumentByName(instrument.getName()));
		}
		musicSheet.setInstruments(strumenti);
		try {
			MusicSheet ms = musicSheetRepository.save(musicSheet);
			MusicSheetData data = musicSheet.getData();
			data.setId(ms.getId().toString());
			musicSheetDataRepository.save(data);
			return ms;
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public MusicSheet findMusicSheetByTitle(String title) throws BusinessException {
		try {
			return musicSheetRepository.findMusicSheetByTitle(title);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public MusicSheet verifyMusicSheet(Long id) throws BusinessException {
		MusicSheet target = musicSheetRepository.findMusicSheetById(id);
		target.setVerified(true);
		return musicSheetRepository.save(target);
	}

	// @Override
	// public Spartito findSpartitoByContenuto(String contenuto) throws
	// BusinessException {
	// // tablature o spartiti
	// return null;
	// }

	@Override
	public MusicSheet findMusicSheetVerified(String title) throws BusinessException {
		try {
			MusicSheet musicSheet = musicSheetRepository.findMusicSheetByTitle(title);
			if (musicSheet.getVerified()) {
				return musicSheet;
			}
			return null;
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare lo spartito \n"+e.getMessage());
		}
	}

	@Override
	public MusicSheet update(MusicSheet musicSheet) throws BusinessException {
		try {
			musicSheetDataRepository.save(musicSheet.getData());
			return musicSheetRepository.save(musicSheet);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void deleteMusicSheetById(Long id) throws BusinessException {
		try {
			musicSheetRepository.deleteById(id);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> getMostPopularMusicSheets() throws BusinessException {
		try {
			return musicSheetRepository.getMostPopularMusicSheets(PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> getLastInsertMusicSheets() throws BusinessException {
		try {
			return musicSheetRepository.getLastInsert(PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> getMusicSheetsBySong(Song song) throws BusinessException {
		try {
			return musicSheetRepository.getMusicSheetsBySong(song, PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> getMusicSheetsByGenre(Genre genre) throws BusinessException {
		try {
			return musicSheetRepository.getMusicSheetsByGenre(genre, PageRequest.of(0, 3)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());			
		}
	}

	@Override
	public Page<MusicSheet> getAllMusicSheets(Pageable pageable) throws BusinessException {
		try {
			return musicSheetRepository.findAll(pageable);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> searchMusicSheetsByTitle(String title) throws BusinessException {
		try {
			return musicSheetRepository.searchMusicSheetsByTitle(title, PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> searchMusicSheetsByVerified(Boolean verified) throws BusinessException {
		try {
			return musicSheetRepository.searchMusicSheetsByVerified(verified, PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public List<MusicSheet> searchMusicSheetsByUserUsername(String username) throws BusinessException {
		try {
			return musicSheetRepository.searchMusicSheetsByUserUsername(username,PageRequest.of(0, 5)).toList();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public Page<MusicSheet> searchMusicSheets(String search, String sortBy, String sortDirection, List<String> genres, List<String> instruments, Boolean verified, Boolean rearranged, int pageNumber, int pageSize) throws BusinessException{
		String q = "SELECT ms FROM MusicSheet ms ";
		if(!instruments.isEmpty()) q+="JOIN ms.instruments i ";
		boolean whereClause = false;
		
		//SEARCH STRING
		if(search != null && search.length() > 0){
			q+="WHERE (ms.title LIKE CONCAT('%',:search,'%') OR ms.song.title LIKE CONCAT('%',:search,'%') OR ms.song.author LIKE CONCAT('%',:search,'%')) ";
			whereClause = true;
		}
		//GENERES
		if(!genres.isEmpty() && !whereClause){
			q += "WHERE ms.s.genre.name in :genres ";
			whereClause = true;
		}
		else if(!genres.isEmpty() && whereClause){
			q += "AND ms.s.genre.name in :genres ";
		}
		//INSTRUMENTS
		if(!instruments.isEmpty() && !whereClause){
			q += "WHERE i.name in :instruments ";
			whereClause = true;
		}else if (!instruments.isEmpty() && whereClause){
			q += "AND i.name in :instruments ";
		}
		//VERIFIED
		if(verified != null && verified && !whereClause){
			q += "WHERE ms.verified = :verified ";
			whereClause = true;
		}else if(verified != null && verified && whereClause){
			q += "AND ms.verified = :verified ";
		}
		//REARRANGED
		if(rearranged != null && rearranged && !whereClause){
			q += "WHERE ms.rearranged = :rearranged ";
			whereClause = true;
		}else if(rearranged != null && rearranged && whereClause){
			q += "AND ms.rearranged = :rearranged ";
		}
		//ORDER BY
		if (sortBy != null){
			q += "ORDER BY ms."+sortBy+" "+sortDirection;
		}
		//System.out.println(q);
		
		Query query =  em.createQuery(q);
		if(search != null && search.length() > 0) query.setParameter("search", search);
		if(!genres.isEmpty()) query.setParameter("genres", genres);
		if(!instruments.isEmpty()) query.setParameter("instruments", instruments);
		if(verified != null && verified) query.setParameter("verified", verified);
		if(rearranged != null && rearranged) query.setParameter("rearranged", rearranged);
		
		try {
			List<MusicSheet> musicSheets = query.getResultList();
			int toIndex = (((pageNumber*pageSize)+pageSize) <= musicSheets.size()) ? (pageNumber*pageSize)+pageSize : musicSheets.size();
			return new PageImpl<MusicSheet>(musicSheets.subList(pageNumber*pageSize, toIndex), PageRequest.of(pageNumber, pageSize), musicSheets.size());
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void addLike(MusicSheet musicSheet, User user) throws BusinessException {
		Set<User> likes = musicSheet.getLikes();
		likes.add(user);
		musicSheet.setLikes(likes);
		try {
			musicSheetRepository.save(musicSheet);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public void removeLike(MusicSheet musicSheet, User user) throws BusinessException {
		Set<User> likes = musicSheet.getLikes();
		likes.remove(user);
		musicSheet.setLikes(likes);
		try {
			musicSheetRepository.save(musicSheet);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

}
