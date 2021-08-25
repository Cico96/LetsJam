package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import it.univaq.disim.mwt.letsjam.business.BranoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.BranoRepository;
import it.univaq.disim.mwt.letsjam.domain.Brano;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public class BranoServiceImpl implements BranoService {
	@Autowired
	private BranoRepository branoRepository;

	@Override
	public Brano findBranoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoById(id);
	}

	@Override
	public Brano findBranoByTitolo(String titolo) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoByTitolo(titolo);
	}

	@Override
	public Brano findBranoByAutore(String autore) throws BusinessException {
		// TODO Auto-generated method stub
		return branoRepository.findBranoByAutore(autore);
	}

}
