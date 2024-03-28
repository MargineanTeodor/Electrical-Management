package ChatBackend.repository;

import ChatBackend.model.Chat;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    List<Chat> findAllByReceiverAndSender(Long receiver,Long Sender);
    Chat findFirstById(Long id);
}