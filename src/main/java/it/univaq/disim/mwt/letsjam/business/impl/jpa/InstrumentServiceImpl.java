package it.univaq.disim.mwt.letsjam.business.impl.jpa;

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
	public Instrument findStrumentoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findStrumentoById(id);
	}

	@Override
	public Instrument findStrumentoByNome(String nome) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findStrumentoByNome(nome);
	}

}
