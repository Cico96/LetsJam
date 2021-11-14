package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.CommentService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.CommentRepository;
import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	

	@Override
	public Comment findCommentById(Long id) throws BusinessException {
		try {
			return commentRepository.findCommentById(id);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public Comment update(Comment comment) throws BusinessException {
		try {
			return commentRepository.save(comment);
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}	
	}

	@Override
	public Comment addComment(Comment comment) throws BusinessException {
		try {
			return commentRepository.save(comment);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Comment> getMusicSheetComments(Long musicSheetId) throws BusinessException {
		try {
			return commentRepository.getMusicSheetComments(musicSheetId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@Override
	public List<Comment> getReplies(Long parentId) throws BusinessException {
		try {
			return commentRepository.getReplies(parentId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
