package it.univaq.disim.mwt.letsjam.business;

import it.univaq.disim.mwt.letsjam.domain.ChatMessage;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;

import java.util.List;

public interface ConversationService {

    void addConversation(Conversation conversation);

    List<Conversation> findAllConversation(User user);

    void addMessaggio(ChatMessage message);
}
