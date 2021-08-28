package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface StrumentoService {
	
	Instrument findStrumentoById(Long id) throws BusinessException;
	
	Instrument findStrumentoByNome(String nome) throws BusinessException;
}
