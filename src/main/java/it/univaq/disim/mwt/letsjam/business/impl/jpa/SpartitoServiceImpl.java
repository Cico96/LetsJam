package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.SpartitoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SpartitoDataRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SpartitoRepository;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.SpartitoData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class SpartitoServiceImpl implements SpartitoService {
    
    @Autowired
	private SpartitoRepository spartitoRepository;

    @Autowired
    private SpartitoDataRepository spartitoDataRepository;
    
    @Override
    public Spartito findSpartitoById(Long id) throws BusinessException {

        SpartitoData data = spartitoDataRepository.findById(id.toString()).get();
		Spartito spartito = spartitoRepository.findById(id).get();
        spartito.setData(data);
        return spartito;
	}
    
    @Override
    public Spartito addSpartito(Spartito spartito) throws BusinessException{
        Spartito s = spartitoRepository.save(spartito);
        SpartitoData data = spartito.getData();
        data.setId(spartito.getId().toString());
        spartitoDataRepository.save(data);
        return spartito;
    }

	@Override
	public Spartito findSpartitoByTitolo(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.findSpartitoByTitolo(titolo);
	}

//	@Override
//	public Spartito findSpartitoByContenuto(String contenuto) throws BusinessException {
//		// tablature o spartiti
//		return null;
//	}

	@Override
	public Spartito findSpartitoByVerificato(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		Spartito spartito = spartitoRepository.findSpartitoByTitolo(titolo);
		if(spartito.getVerificato()) {
			return spartito;
		}
		return null;
	}

	@Override
	public void update(Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteSpartitoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		spartitoRepository.deleteById(id);
		
	}

	@Override
	public Spartito insert(Spartito spartito) throws BusinessException {
		// TODO Auto-generated method stub
		return spartitoRepository.save(spartito);
	}

}
