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

import java.util.ArrayList;
import java.util.List;

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
        return conversationRepository.findConversationBySender(user);
    }

    @Override
    public Message addMessage(Message message) {
        return chatMessageRepository.save(message);
    }

    @Override
    public List<User> getUsersAlreadyTalking(User user) {
        List<Conversation> conversations = conversationRepository.findConversationBySender(user);
        List<User> users = new ArrayList<User>();
        conversations.forEach(c->{
            if(c.getSender().equals(user)){
                users.add(c.getReceiver());
            }else{
                users.add(c.getSender());
            }
        });
        return users;
    }

    @Override
    public Conversation findConversationById(Long id) {
        return conversationRepository.findConversationById(id);
    }
}
