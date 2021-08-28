package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.CommentService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.CommentRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MusicSheetRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UserRepository;
import it.univaq.disim.mwt.letsjam.domain.Comment;
import it.univaq.disim.mwt.letsjam.domain.MusicSheet;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired MusicSheetRepository musicSheetRepositoryRepository;

	@Override
	public Comment findCommentById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return commentRepository.findCommentById(id);
	}

	@Override
	public void update(Comment comment) throws BusinessException {
		// TODO Auto-generated method stub
		commentRepository.save(comment);
	}

	@Override
	public void addComment(Long id_musicSheet, Long id_user, String content) throws BusinessException {
		// TODO Auto-generated method stub
		Comment new_commento = new Comment();
		MusicSheet spartito = musicSheetRepositoryRepository.findMusicSheetById(id_musicSheet);
		User user = userRepository.findUserById(id_user);
		new_commento.setSpartito(spartito);
		new_commento.setUser(user);
		new_commento.setContenuto(content);
		commentRepository.save(new_commento);
	}

	@Override
	public void addAnsewer(Comment comment, Long id_user, String content) {
		// TODO Auto-generated method stub
		Comment risposta = new Comment();
		risposta.setContenuto(content);
		User user = userRepository.findUserById(id_user);
		risposta.setUser(user);
		risposta.setCommentoPadre(comment);
		commentRepository.save(risposta);
	}

}
