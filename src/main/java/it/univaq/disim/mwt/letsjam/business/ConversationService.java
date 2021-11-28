package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Message;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import java.util.List;

public interface ConversationService {

    Conversation addConversation(Conversation conversation) throws BusinessException;;

    List<Conversation> findAllConversation(User user) throws BusinessException;;

    Message addMessage(Message message) throws BusinessException;;

    List<User> getUsersAlreadyTalking(User loggedUser);

    List<User> getUsersNotYetTalking(User loggedUser);

    Conversation findConversationById(Long id) throws BusinessException;;
}
