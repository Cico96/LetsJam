package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Song;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface SongService {
	
	Song findBranoById(Long id) throws BusinessException;
	
	Song findBranoByTitolo(String titolo) throws BusinessException;
	
	Song findBranoByAutore(String autore) throws BusinessException;
}
