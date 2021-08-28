package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface InstrumentService {
	
	Instrument findInsstrumentById(Long id) throws BusinessException;
	
	Instrument findInstrumentByNome(String name) throws BusinessException;
}
