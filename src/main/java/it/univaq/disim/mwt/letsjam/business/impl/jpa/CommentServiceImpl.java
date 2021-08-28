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
	private CommentRepository commentoRepository;
	
	@Autowired
	private UserRepository utenteRepository;
	
	@Autowired MusicSheetRepository spartitoRepository;

	@Override
	public Comment findCommentoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return commentoRepository.findCommentoById(id);
	}

	@Override
	public void update(Comment commento) throws BusinessException {
		// TODO Auto-generated method stub
		commentoRepository.save(commento);
	}

	@Override
	public void addCommento(Long id_spartito, Long id_utente, String contenuto) throws BusinessException {
		// TODO Auto-generated method stub
		Comment new_commento = new Comment();
		MusicSheet spartito = spartitoRepository.findSpartitoById(id_spartito);
		User user = utenteRepository.findUtenteById(id_utente);
		new_commento.setMusicSheet(spartito);
		new_commento.setUser(user);
		new_commento.setContent(contenuto);
		commentoRepository.save(new_commento);
	}

	@Override
	public void addRisposta(Comment commento, Long id_utente, String contenuto) {
		// TODO Auto-generated method stub
		Comment risposta = new Comment();
		risposta.setContent(contenuto);
		User user = utenteRepository.findUtenteById(id_utente);
		risposta.setUser(user);
		risposta.setParentComment(commento);
		commentoRepository.save(risposta);
	}

}
