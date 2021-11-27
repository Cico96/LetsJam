package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import it.univaq.disim.mwt.letsjam.business.ConversationService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MessageRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.ConversationRepository;
import it.univaq.disim.mwt.letsjam.domain.Message;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private MessageRepository chatMessageRepository;

    @Autowired
    private UserService userService;

    @Override
    public Conversation addConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public List<Conversation> findAllConversation(User user) {
        List<Conversation> conversations = conversationRepository.findConversationBySender(user.getId());
        conversations.forEach(c -> {
            c.setSender(userService.findUserById(c.getSenderId()));
            c.setReceiver(userService.findUserById(c.getReceiverId()));
            Set<Message> messages = c.getMessages();
            messages.forEach(m -> m.setSender(userService.findUserById(m.getSenderId())));
            c.setMessages(messages);
        });
        return conversations;
    }

    @Override
    public Message addMessage(Message message) {
        return chatMessageRepository.save(message);
    }
}
