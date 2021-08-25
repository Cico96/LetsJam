package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import it.univaq.disim.mwt.letsjam.business.CommentoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.CommentoRepository;
import it.univaq.disim.mwt.letsjam.domain.Commento;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public class CommentoServiceImpl implements CommentoService {
	@Autowired
	private CommentoRepository commentoRepository;

	@Override
	public Commento findCommentoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return commentoRepository.findCommentoById(id);
	}

	@Override
	public void update(Commento commento) throws BusinessException {
		// TODO Auto-generated method stub
		commentoRepository.save(commento);
	}

}
