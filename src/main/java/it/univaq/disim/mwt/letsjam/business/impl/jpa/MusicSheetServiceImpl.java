package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import javax.transaction.Transactional;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetDataRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

@Service
@Transactional
public class MusicSheetServiceImpl implements MusicSheetService {
    
    @Autowired
	private MusicSheetRepository musicSheetRepository;

    @Autowired
    private MusicSheetDataRepository musicSheetDataRepository;
    
    @Override
    public MusicSheet findMusicSheetById(Long id) throws BusinessException {
		MusicSheet musicSheet = musicSheetRepository.findById(id).get();
        return musicSheet;
	}

	@Override
	public MusicSheetData getMusicSheetData(Long id) throws BusinessException{
		MusicSheetData data = musicSheetDataRepository.findById(id.toString()).get();
		return data;
	}
    
    @Override
    public MusicSheet addMusicSheet(MusicSheet musicSheet) throws BusinessException{
        MusicSheet ms = musicSheetRepository.save(musicSheet);
        MusicSheetData data = musicSheet.getData();
        data.setId(musicSheet.getId().toString());
        musicSheetDataRepository.save(data);
        return musicSheet;
    }

	@Override
	public MusicSheet findMusicSheetByTitle(String title) throws BusinessException {
		// TODO Auto-generated method stub
		return musicSheetRepository.findMusicSheetByTitle(title);
	}

//	@Override
//	public Spartito findSpartitoByContenuto(String contenuto) throws BusinessException {
//		// tablature o spartiti
//		return null;
//	}

	@Override
	public MusicSheet findMusicSheetVerified(String title) throws BusinessException {
		// TODO Auto-generated method stub
		MusicSheet musicSheet = musicSheetRepository.findMusicSheetByTitle(title);
		if(musicSheet.getVerified()) {
			return musicSheet;
		}
		return null;
	}

	@Override
	public void update(MusicSheet musicSheet) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteMusicSheetById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		musicSheetRepository.deleteById(id);
		
	}

	@Override
	public MusicSheet insert(MusicSheet musicSheet) throws BusinessException {
		// TODO Auto-generated method stub
		return musicSheetRepository.save(musicSheet);
	}

	@Override
	public List<MusicSheet> getMostPopularMusicSheets() throws BusinessException{
		Page<MusicSheet> spartiti = musicSheetRepository.getMostPopularMusicSheets(PageRequest.of(0, 5));
		return spartiti.toList();
	}

	@Override
	public List<MusicSheet> getLastInsertMusicSheets() throws BusinessException {
    	Page<MusicSheet> spartiti = musicSheetRepository.getLastInsert(PageRequest.of(0,5));
		return spartiti.toList();
	}

	@Override
	public List<MusicSheet> getMusicSheetsBySong(Song song) throws BusinessException {
		Page<MusicSheet> spartiti = musicSheetRepository.getMusicSheetsBySong(song, PageRequest.of(0,5));
		return spartiti.toList();
	}

	@Override
	public List<MusicSheet> getMusicSheetsByGenre(Genre genre) throws BusinessException {
		return musicSheetRepository.getMusicSheetsByGenre(genre, PageRequest.of(0,3)).toList();
	}

	@Override
	public List<MusicSheet> getAllMusicSheets() throws BusinessException {
		return musicSheetRepository.findAll();
	}
	
	@Override	
	public List<MusicSheet> searchMusicSheetsByTitle(String title) throws BusinessException {
    	Page<MusicSheet> musicSheets = musicSheetRepository.searchMusicSheetsByTitle(title, PageRequest.of(0,5));
		return musicSheets.toList();
	}

	@Override
	public List<MusicSheet> searchMusicSheetsByVerified(Boolean verified) throws BusinessException {
		Page<MusicSheet> musicSheets = musicSheetRepository.searchMusicSheetsByVerified(verified, PageRequest.of(0,5));
		return musicSheets.toList();
	}

	@Override
	public List<MusicSheet> searchMusicSheetsByUserUsername(String username) throws BusinessException {
		Page<MusicSheet> musicSheets = musicSheetRepository.searchMusicSheetsByUserUsername(username, PageRequest.of(0,5));
		return musicSheets.toList();
	}


}
