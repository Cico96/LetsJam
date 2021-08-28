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
    public MusicSheet findSpartitoById(Long id) throws BusinessException {
		MusicSheet spartito = spartitoRepository.findById(id).get();
        return spartito;
	}

	@Override
	public MusicSheetData getSpartitoData(Long id) throws BusinessException{
		MusicSheetData data = spartitoDataRepository.findById(id.toString()).get();
		return data;
	}
    
    @Override
    public MusicSheet addSpartito(MusicSheet spartito) throws BusinessException{
        MusicSheet s = spartitoRepository.save(spartito);
        MusicSheetData data = spartito.getData();
        data.setId(spartito.getId().toString());
        spartitoDataRepository.save(data);
        return spartito;
    }

	@Override
	public MusicSheet findSpartitoByTitolo(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.findSpartitoByTitolo(titolo);
	}

//	@Override
//	public Spartito findSpartitoByContenuto(String contenuto) throws BusinessException {
//		// tablature o spartiti
//		return null;
//	}

	@Override
	public MusicSheet findSpartitoByVerificato(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		MusicSheet spartito = spartitoRepository.findSpartitoByTitolo(titolo);
		if(spartito.getVerificato()) {
			return spartito;
		}
		return null;
	}

	@Override
	public void update(MusicSheet spartito) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteSpartitoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		spartitoRepository.deleteById(id);
		
	}

	@Override
	public MusicSheet insert(MusicSheet spartito) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.save(spartito);
	}

	public Set<MusicSheet> getSpartitiFromGenere(Genre g) throws BusinessException{
		return null;
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
