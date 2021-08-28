package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.BranoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.BranoRepository;
import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
@Service
public class BranoServiceImpl implements BranoService {
	@Autowired
	private BranoRepository branoRepository;

	@Override
	public Song findBranoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoById(id);
	}

	@Override
	public Song findBranoByTitolo(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoByTitolo(titolo);
	}

	@Override
	public Song findBranoByAutore(String autore) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoByAutore(autore);
	}

}
