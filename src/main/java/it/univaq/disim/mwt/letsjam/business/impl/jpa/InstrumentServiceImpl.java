package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.InstrumentService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.InstrumentRepository;
import it.univaq.disim.mwt.letsjam.domain.Instrument;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
@Service
public class InstrumentServiceImpl implements InstrumentService {
	@Autowired
	private InstrumentRepository strumentoRepository;

	@Override
	public Instrument insert(Instrument instrument) throws BusinessException {
		return strumentoRepository.save(instrument);
	}

	@Override
	public Instrument findInsstrumentById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findInstrumentById(id);
	}

	@Override
	public Instrument findInstrumentByNome(String name) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findInstrumentByName(name);
	}

	@Override
	public List<Instrument> getAllInstruments() throws BusinessException {
		return strumentoRepository.findAll();
	}

}
