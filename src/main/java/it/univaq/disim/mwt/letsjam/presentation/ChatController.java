package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.ConversationService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
import it.univaq.disim.mwt.letsjam.domain.Message;
import it.univaq.disim.mwt.letsjam.domain.User;
import it.univaq.disim.mwt.letsjam.security.CustomUserDetails;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private UserService userService;

    @PostMapping("/findConversations")
    public ResponseEntity<String> showAllConversation(Authentication authentication){
        User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        List<Conversation> conversations = conversationService.findAllConversation(loggedUser);
        conversations = new ArrayList<Conversation>(new LinkedHashSet<Conversation>(conversations));
        JSONArray result = new JSONArray();

        conversations.forEach(c -> {
            JSONObject obj = new JSONObject();
            obj.put("username", c.getReceiver().getUsername());
            obj.put("conversationId", c.getConversationId());
            result.put(obj);
        });
        return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
    }

    @PostMapping("/getUsersNotYetTalking")
    public ResponseEntity<String> getUsersNotYetTalking(Authentication authentication){
        User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        List<User> notYetTalkingUsers = conversationService.getUsersNotYetTalking(loggedUser);

        JSONArray result = new JSONArray();
        notYetTalkingUsers.forEach(user -> {
            JSONObject obj = new JSONObject();
            obj.put("username",user.getUsername());
            obj.put("id", user.getId());
            result.put(obj);
        });

        return new ResponseEntity<String> (result.toString(), HttpStatus.OK);
    }

    @PostMapping("/addMessage")
    public ResponseEntity<String> addMessage(@RequestParam("content") String content, @RequestParam("conversationId") String id, Authentication authentication){
        User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        Conversation conversation = conversationService.findConversationById(Long.parseLong(id));
        Message message = new Message();
        message.setContent(content);
        message.setSender(loggedUser);
        Set<Message> messages = (conversation.getMessages() == null)? new HashSet<Message>() : conversation.getMessages();
        messages.add(message);
        conversation.setMessages(messages);
        conversationService.addConversation(conversation);
        return new ResponseEntity<String>("Il messaggio è stato inviato correttamente", HttpStatus.OK);
    }

    @PostMapping("/addConversation")
    public ResponseEntity<String> addConversation(@RequestParam("username") String username, Authentication authentication){
        User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        User user = userService.findUserByUsername(username);
        Conversation conversation = new Conversation();
        conversation.setReceiver(user);
        conversation.setSender(loggedUser);
        conversationService.addConversation(conversation);
        return new ResponseEntity<String>("La conversazione è stata inserita correttamente", HttpStatus.OK);
    }
}
