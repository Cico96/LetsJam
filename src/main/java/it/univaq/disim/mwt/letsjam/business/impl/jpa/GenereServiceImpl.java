package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.univaq.disim.mwt.letsjam.business.GenereService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.GenereRepository;
import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
public class GenereServiceImpl implements GenereService {
	@Autowired 
	private GenereRepository genereRepository;
	
	@Override
	public Genre findGenereById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return genereRepository.findGenereById(id);
	}

	@Override
	public Genre findGenereByNome(String nome) throws BusinessException {
		// TODO Auto-generated method stub
		return genereRepository.findGenereByNome(nome);
	}

}
