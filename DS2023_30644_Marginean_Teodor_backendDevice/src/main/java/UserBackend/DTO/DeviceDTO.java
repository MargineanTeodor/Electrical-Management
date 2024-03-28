package UserBackend.DTO;

import UserBackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceDTO {
    private Long id;
    private String description;
    private String address;
    private Long maximHrEnergyConsumption;
}
