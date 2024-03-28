package ChatBackend.controller;

import ChatBackend.model.Chat;
import ChatBackend.service.ServiceChat;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ServiceChat serviceChat;

    public ChatController(ServiceChat serviceChat) {
        this.serviceChat = serviceChat;
    }

    @RequestMapping(value = "/addChat", method = RequestMethod.PUT)
    public void addChatLine(@RequestParam String message, Long receiver, Long sender) {
        serviceChat.addChatLine(message, receiver, sender);
    }

    @RequestMapping(value = "/setSeen", method = RequestMethod.PUT)
    public void getNormalUsers(@RequestParam Long id) {
        serviceChat.setSeen(id);
    }

    @RequestMapping(value = "/getChat", method = RequestMethod.GET)
    public List<Chat> changePassword(@RequestParam Long id, Long id2) throws JsonProcessingException {
        List<Chat> chats = this.serviceChat.listOfChats(id,id2);
        return chats;
    }
}
