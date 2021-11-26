package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import it.univaq.disim.mwt.letsjam.business.ConversationService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.ChatMessageRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.ConversationRepository;
import it.univaq.disim.mwt.letsjam.domain.ChatMessage;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void addConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    @Override
    public List<Conversation> findAllConversation(User user) {
        return conversationRepository.findConversationBySender(user);
    }

    @Override
    public void addMessaggio(ChatMessage message) {
        chatMessageRepository.save(message);
    }
}
