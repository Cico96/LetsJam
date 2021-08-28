package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.MusicSheetService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetDataRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.domain.MusicSheetData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MusicSheetServiceImpl implements MusicSheetService {
    
    @Autowired
	private MusicSheetRepository spartitoRepository;

    @Autowired
    private MusicSheetDataRepository spartitoDataRepository;
    
    @Override
    public MusicSheet findMusicSheetById(Long id) throws BusinessException {
		MusicSheet musicSheet = spartitoRepository.findById(id).get();
        return musicSheet;
	}

	@Override
	public MusicSheetData getMusicSheetData(Long id) throws BusinessException{
		MusicSheetData data = spartitoDataRepository.findById(id.toString()).get();
		return data;
	}
    
    @Override
    public MusicSheet addMusicSheet(MusicSheet musicSheet) throws BusinessException{
        MusicSheet ms = spartitoRepository.save(musicSheet);
        MusicSheetData data = musicSheet.getData();
        data.setId(musicSheet.getId().toString());
        spartitoDataRepository.save(data);
        return musicSheet;
    }

	@Override
	public MusicSheet findMusicSheetByTitol(String titol) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.findSpartitoByTitolo(titol);
	}

//	@Override
//	public Spartito findSpartitoByContenuto(String contenuto) throws BusinessException {
//		// tablature o spartiti
//		return null;
//	}

	@Override
	public MusicSheet findMusicSheetVerified(String titol) throws BusinessException {
		// TODO Auto-generated method stub
		MusicSheet musicSheet = spartitoRepository.findSpartitoByTitolo(titol);
		if(musicSheet.getVerificato()) {
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
		spartitoRepository.deleteById(id);
		
	}

	@Override
	public MusicSheet insert(MusicSheet musicSheet) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.save(musicSheet);
	}

	@Override
	public List<MusicSheet> getMostPopularMusicSheets() throws BusinessException{
		Page<MusicSheet> spartiti = spartitoRepository.getMostPopularMusicSheets(PageRequest.of(0, 5));
		return spartiti.toList();
	}

	@Override
	public List<MusicSheet> getLastInsertMusicSheets() throws BusinessException {
    	Page<MusicSheet> spartiti = spartitoRepository.getLastInsert(PageRequest.of(0,5));
		return spartiti.toList();
	}


}
