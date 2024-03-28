package UserBackend.service;

import UserBackend.DTO.DeviceDTO;
import UserBackend.mapper.DeviceMapper;
import UserBackend.model.Device;
import UserBackend.model.User;
import UserBackend.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDevice {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    public ServiceDevice(DeviceRepository deviceRepository, DeviceMapper deviceMapper)
    {
        this.deviceRepository=  deviceRepository;
        this.deviceMapper = deviceMapper;
    }
    public List<DeviceDTO> getAllDevicesForUser(User user)
    {
        List<Device> listOfDevices = this.deviceRepository.findAllByUser(user);
        List<DeviceDTO> deviceDTOList = new ArrayList<DeviceDTO>();
        for(Device device: listOfDevices)
        {
            deviceDTOList.add(DeviceMapper.mapModelToDto(device));
        }
        return deviceDTOList;
    }
    public void addDevice( String description,String address, Long maximHrEnergyConsumption, User user)
    {
        Device device = new Device();
        device.setUser(user);
        device.setAddress(address);
        device.setDescription(description);
        device.setMaximHrEnergyConsumption(maximHrEnergyConsumption);
        deviceRepository.save(device);
    }
}
