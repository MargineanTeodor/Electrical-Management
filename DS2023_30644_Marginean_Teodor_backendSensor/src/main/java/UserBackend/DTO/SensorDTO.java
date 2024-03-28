package UserBackend.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensorDTO {
    private Long id;
    private float value;
    private Date timestamp;
    private int hour;
    private Long device_id;
}
