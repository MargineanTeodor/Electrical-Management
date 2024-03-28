package ChatBackend.mapper;

import ChatBackend.DTO.ChatDTO;
import ChatBackend.model.Chat;
import org.springframework.stereotype.Component;

@Component
public class ChatMapper {
    public static ChatDTO mapModelToDto(Chat chat)
    {
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setReceiver(chat.getReceiver());
        chatDTO.setMessage(chat.getMessage());
        chatDTO.setSender(chat.getSender());
        chatDTO.setId(chat.getId());
        chatDTO.setSeen(chat.getSeen());
        return chatDTO;
    }
    public static Chat mapDtoToModel(ChatDTO chatDTO)
    {
        Chat chat = new Chat();
        chat.setReceiver(chatDTO.getReceiver());
        chat.setMessage(chatDTO.getMessage());
        chat.setSender(chatDTO.getSender());
        chat.setId(chatDTO.getId());
        chat.setSeen(chatDTO.getSeen());
        return chat;
    }
}
