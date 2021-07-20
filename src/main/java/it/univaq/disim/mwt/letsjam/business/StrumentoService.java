package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Strumento;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface StrumentoService {
	
	Strumento findStrumentoById(Long id) throws BusinessException;
	
	Strumento findStrumentoByNome(String nome) throws BusinessException;
}
