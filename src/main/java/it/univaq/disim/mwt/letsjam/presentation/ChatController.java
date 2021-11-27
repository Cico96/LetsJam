package it.univaq.disim.mwt.letsjam.presentation;

import it.univaq.disim.mwt.letsjam.business.ConversationService;
import it.univaq.disim.mwt.letsjam.business.UserService;
import it.univaq.disim.mwt.letsjam.domain.Conversation;
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

import java.util.List;

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
        JSONArray result = new JSONArray();

        conversations.forEach(c -> {
            JSONObject obj = new JSONObject();
            obj.put("username", c.getReceiver().getUsername());
            result.put(obj);
        });
        return new ResponseEntity<String>(result.toString(), HttpStatus.OK);
    }

    @PostMapping("/getUsersNotYetTalking")
    public ResponseEntity<String> getUsersNotYetTalking(Authentication authentication){
        User loggedUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        List<User> users = conversationService.getUsersAlreadyTalking(loggedUser);
        List<User> allUsers = userService.getAllUsers();
        List<User> allUsersCopy = userService.getAllUsers();
        System.out.println(conversationService.getUsersAlreadyTalking(loggedUser).size());
        allUsers.forEach(user -> {
            if(user.equals(loggedUser)){
                allUsersCopy.remove(user);
            }
            users.forEach(u->{
               if(user.equals(u)){
                   allUsersCopy.remove(user);
               }
            });
        });
        JSONArray result = new JSONArray();
        allUsersCopy.forEach(user -> {
            JSONObject obj = new JSONObject();
            obj.put("username",user.getUsername());
            obj.put("id", user.getId());
            result.put(obj);
        });

        return new ResponseEntity<String> (result.toString(), HttpStatus.OK);
    }
}
