package UserBackend.mapper;

import UserBackend.DTO.DeviceDTO;
import UserBackend.model.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {
    public static DeviceDTO mapModelToDto(Device device)
    {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(device.getId());
        deviceDTO.setAddress(device.getAddress());
        deviceDTO.setDescription(device.getDescription());
        deviceDTO.setMaximHrEnergyConsumption(device.getMaximHrEnergyConsumption());
        return deviceDTO;
    }
    public static Device mapDtoToModel( DeviceDTO deviceDTO)
    {
        Device device = new Device();
        device.setId(deviceDTO.getId());
        device.setAddress(deviceDTO.getAddress());
        device.setDescription(deviceDTO.getDescription());
        device.setMaximHrEnergyConsumption(deviceDTO.getMaximHrEnergyConsumption());
        return device;
    }
}
