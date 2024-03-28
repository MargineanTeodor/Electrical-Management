package ChatBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatDTO {
    private Long id;
    private String message;
    private Long receiver;
    private Long sender;
    private Boolean seen;
}
