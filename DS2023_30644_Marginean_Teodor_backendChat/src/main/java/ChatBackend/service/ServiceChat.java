package ChatBackend.service;

import ChatBackend.mapper.ChatMapper;
import ChatBackend.model.Chat;
import ChatBackend.repository.ChatRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceChat {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    public ServiceChat(ChatRepository chatRepository, ChatMapper chatMapper)
    {
        this.chatRepository= chatRepository;
        this.chatMapper = chatMapper;
    }
    public void addChatLine(String message, Long sender,Long receiver)
    {
        Chat x = new Chat();
        x.setReceiver(receiver);
        x.setMessage(message);
        x.setSender(sender);
        x.setSeen(Boolean.FALSE);
        this.chatRepository.save(x);
        System.out.println("Succes");
    }
    public  List<Chat>listOfChats(Long id,Long id2) throws JsonProcessingException {
        List<Chat> chats = new ArrayList<Chat>();
        List<Chat> chats2 = new ArrayList<Chat>();
        chats = this.chatRepository.findAllByReceiverAndSender(id,id2);
        chats2 = this.chatRepository.findAllByReceiverAndSender(id2,id);
        chats.addAll(chats2);
        Collections.sort(chats, new Comparator<Chat>() {
            @Override
            public int compare(Chat c1, Chat c2) {
                return Long.compare(c1.getId(), c2.getId());
            }
        });
        return chats;
    }
    public void setSeen(Long id)
    {
        Chat chat = this.chatRepository.findFirstById(id);
        chat.setSeen(Boolean.TRUE);
        this.chatRepository.save(chat);
    }
}
