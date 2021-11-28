package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.Message;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;

import java.util.List;

public interface ConversationService {

    Conversation addConversation(Conversation conversation);

    List<Conversation> findAllConversation(User user);

    Message addMessage(Message message);

    List<User> getUsersAlreadyTalking(User loggedUser);

    List<User> getUsersNotYetTalking(User loggedUser);

    Conversation findConversationById(Long id);
}
