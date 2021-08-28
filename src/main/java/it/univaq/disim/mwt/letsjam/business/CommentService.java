package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface CommentService {
	
	void addComment(Long id_musicSheet, Long id_user, String content) throws BusinessException;
	
	Comment findCommentById(Long id) throws BusinessException;
	
	void addAnsewer(Comment comment, Long id_user, String contenut) throws BusinessException;
	
	void update(Comment comment) throws BusinessException;
	
}
