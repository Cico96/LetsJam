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
		try {
			return strumentoRepository.save(instrument);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public Instrument findInsstrumentById(Long id) throws BusinessException {
		try {
			return strumentoRepository.findInstrumentById(id);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare lo strumento specificato \n"+e.getMessage());
		}
	}

	@Override
	public Instrument findInstrumentByName(String name) throws BusinessException {
		try {
			return strumentoRepository.findInstrumentByName(name);
		} catch (Exception e) {
			throw new BusinessException("Impossibile trovare lo strumento specificato \n"+e.getMessage());
		}
	}

	@Override
	public List<Instrument> getAllInstruments() throws BusinessException {
		try {
			return strumentoRepository.findAll();
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

	@Override
	public Instrument addInstrument( Instrument instrument) throws BusinessException{
		try {
			return strumentoRepository.save(instrument);
		} catch (Exception e) {
			throw new BusinessException("C'è stato un errore, non è stato possibile completare l'operazione richiesta \n"+e.getMessage());
		}
	}

}
