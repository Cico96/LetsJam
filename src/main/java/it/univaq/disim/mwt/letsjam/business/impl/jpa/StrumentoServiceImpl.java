package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.StrumentoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.StrumentoRepository;
import it.univaq.disim.mwt.letsjam.domain.Strumento;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
@Service
public class StrumentoServiceImpl implements StrumentoService {
	@Autowired
	private StrumentoRepository strumentoRepository;

	@Override
	public Strumento findStrumentoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findStrumentoById(id);
	}

	@Override
	public Strumento findStrumentoByNome(String nome) throws BusinessException {
		// TODO Auto-generated method stub
		return strumentoRepository.findStrumentoByNome(nome);
	}

}
