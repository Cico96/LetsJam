package it.univaq.disim.mwt.letsjam.business;

import java.util.List;

import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

public interface CommentService {
	
	Comment addComment(Comment comment) throws BusinessException;
	
	Comment findCommentById(Long id) throws BusinessException;
	
	Comment update(Comment comment) throws BusinessException;

	List<Comment> getMusicSheetComments(Long musicSheetId) throws BusinessException;

	List<Comment> getReplies(Long parentId) throws BusinessException;
}
