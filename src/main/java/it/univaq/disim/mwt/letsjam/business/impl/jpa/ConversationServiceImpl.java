package it.univaq.disim.mwt.letsjam.business.impl.jpa;

import it.univaq.disim.mwt.letsjam.business.ConversationService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.MessageRepository;
import it.univaq.disim.mwt.letsjam.business.impl.jpa.repository.ConversationRepository;
import it.univaq.disim.mwt.letsjam.domain.Message;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
    public Conversation addConversation(Conversation conversation) throws BusinessException {
        try {
            return conversationRepository.save(conversation);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Conversation> findAllConversation(User user) throws BusinessException {
        try {
            return conversationRepository.findConversationBySender(user);
        } catch (Exception e) {
            throw new BusinessException("Non è stato possibile trovare le conversazioni " + e.getMessage());
        }
    }

    @Override
    public Message addMessage(Message message) throws BusinessException {
        try {
            return chatMessageRepository.save(message);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<User> getUsersAlreadyTalking(User user) throws BusinessException {
        try {
            List<Conversation> conversations = conversationRepository.findConversationBySender(user);
            List<User> users = new ArrayList<User>();
            conversations.forEach(c->{
                if(c.getSender().equals(user)){
                    users.add(c.getReceiver());
                }else{
                    users.add(c.getSender());
                }
            });

            return new ArrayList<User>(new LinkedHashSet<>(users));
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Conversation findConversationById(Long id) {
        try {
            return conversationRepository.findByConversationId(id);
        } catch (Exception e) {
            throw new BusinessException("Non è stato possibile trovare la conversazione " + e.getMessage());
        }
    }
}
