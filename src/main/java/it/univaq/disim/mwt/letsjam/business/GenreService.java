package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Genre;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface GenreService {
	
	
	Genre findGenereById(Long id) throws BusinessException;
	
	Genre findGenereByNome(String nome) throws BusinessException;
	
}
