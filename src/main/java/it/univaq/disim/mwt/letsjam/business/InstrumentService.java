package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface InstrumentService {

	Instrument insert(Instrument instrument) throws BusinessException;
	
	Instrument findInsstrumentById(Long id) throws BusinessException;
	
	Instrument findInstrumentByNome(String name) throws BusinessException;

	List<Instrument> getAllInstruments() throws BusinessException;
	
	Instrument addInstrument( Instrument instrument) throws BusinessException;
}
