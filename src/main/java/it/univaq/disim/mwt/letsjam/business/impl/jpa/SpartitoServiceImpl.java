package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SpartitoDataRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SpartitoRepository;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.SpartitoData;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
@Transactional
public class SpartitoServiceImpl {
    
    @Autowired
	private SpartitoRepository spartitoRepository;

    @Autowired
    private SpartitoDataRepository spartitoDataRepository;
    
    public Spartito findSpartitoById(Long id) throws BusinessException {

        SpartitoData data = spartitoDataRepository.findById(id.toString()).get();
		Spartito spartito = spartitoRepository.findById(id).get();
        spartito.setData(data);
        return spartito;
	}

    public Spartito addSpartito(Spartito spartito) throws BusinessException{
        Spartito s = spartitoRepository.save(spartito);
        SpartitoData data = spartito.getData();
        data.setId(spartito.getId().toString());
        spartitoDataRepository.save(data);
        return spartito;
    }
}
