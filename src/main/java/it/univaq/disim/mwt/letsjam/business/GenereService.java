package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genere;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface GenereService {
	
	
	Genere findGenereById(Long id) throws BusinessException;
	
	Genere findGenereByNome(String nome) throws BusinessException;
	
}
