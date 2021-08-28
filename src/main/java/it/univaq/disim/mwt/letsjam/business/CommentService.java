package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface CommentService {
	
	void addCommento(Long id_spartito, Long id_utente, String contenuto) throws BusinessException;
	
	Comment findCommentoById(Long id) throws BusinessException;
	
	void addRisposta(Comment commento, Long id_utente, String contenuto) throws BusinessException;
	
	void update(Comment commento) throws BusinessException;
	
}
