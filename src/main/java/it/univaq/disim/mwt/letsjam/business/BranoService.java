package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Brano;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface BranoService {
	
	Brano findBranoById(Long id) throws BusinessException;
	
	Brano findBranoByTitolo(String titolo) throws BusinessException;
	
	Brano findBranoByAutore(String autore) throws BusinessException;
	//commento a cazzo di cane
}
