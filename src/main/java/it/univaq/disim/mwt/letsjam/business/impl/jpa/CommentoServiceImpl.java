package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.disim.mwt.letsjam.business.CommentoService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.CommentoRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.SpartitoRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.UtenteRepository;
import it.univaq.disim.mwt.letsjam.domain.Commento;
import it.univaq.disim.mwt.letsjam.domain.Spartito;
import it.univaq.disim.mwt.letsjam.domain.Utente;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

@Service
public class CommentoServiceImpl implements CommentoService {
	@Autowired
	private CommentoRepository commentoRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired SpartitoRepository spartitoRepository;

	@Override
	public Commento findCommentoById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return commentoRepository.findCommentoById(id);
	}

	@Override
	public void update(Commento commento) throws BusinessException {
		// TODO Auto-generated method stub
		commentoRepository.save(commento);
	}

	@Override
	public void addCommento(Long id_spartito, Long id_utente, String contenuto) throws BusinessException {
		// TODO Auto-generated method stub
		Commento new_commento = new Commento();
		Spartito spartito = spartitoRepository.findSpartitoById(id_spartito);
		Utente utente = utenteRepository.findUtenteById(id_utente);
		new_commento.setSpartito(spartito);
		new_commento.setUtente(utente);
		new_commento.setContenuto(contenuto);
		commentoRepository.save(new_commento);
	}

	@Override
	public void addRisposta(Commento commento, Long id_utente, String contenuto) {
		// TODO Auto-generated method stub
		Commento risposta = new Commento();
		risposta.setContenuto(contenuto);
		Utente utente = utenteRepository.findUtenteById(id_utente);
		risposta.setUtente(utente);
		risposta.setCommentoPadre(commento);
		commentoRepository.save(risposta);
	}

}
