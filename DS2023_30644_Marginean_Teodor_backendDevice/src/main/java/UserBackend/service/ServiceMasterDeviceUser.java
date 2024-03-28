package UserBackend.service;

import UserBackend.DTO.DeviceDTO;
import UserBackend.mapper.DeviceMapper;
import UserBackend.mapper.UserMapper;
import UserBackend.model.Device;
import UserBackend.model.User;
import UserBackend.repository.DeviceRepository;
import UserBackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceMasterDeviceUser {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public ServiceMasterDeviceUser(DeviceRepository deviceRepository, DeviceMapper deviceMapper,UserRepository userRepository,UserMapper userMapper)
    {
        this.deviceRepository=  deviceRepository;
        this.deviceMapper = deviceMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public  void deleteDevice(Long idDevice, Long idUser)
    {
            Device device = deviceRepository.findFirstById(idDevice);
            deviceRepository.delete(device);
    }
    @Transactional
    public void deleteUser(Long id)
    {
            User user = userRepository.findUserById(id);
            for(Device device : user.getListOfDevices())
               deleteDevice(device.getId(),user.getId());
            userRepository.delete(user);
    }
}
