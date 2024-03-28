package UserBackend.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TradeWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private int semaphore= 0 ;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        float oldPrice = 0.0f;
        int sem=0;
        while(sem==0)
        {
            String x = "Exceeded consumption";
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(x));
            if(this.semaphore==1) {
                this.semaphore=0;
                session.sendMessage(message);
            }
            Thread.sleep(2000);
        }
        sessions.add(session);
    }
    void change_semaphore()
    {
        semaphore=1;
    }

}
