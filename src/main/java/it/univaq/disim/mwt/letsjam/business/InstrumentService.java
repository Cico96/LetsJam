package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface InstrumentService {

	Instrument insert(Instrument instrument) throws BusinessException;
	
	Instrument findInsstrumentById(Long id) throws BusinessException;
	
	Instrument findInstrumentByNome(String name) throws BusinessException;
}
