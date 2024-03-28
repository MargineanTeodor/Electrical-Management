package UserBackend.controller;

import UserBackend.DTO.DeviceDTO;
import UserBackend.DTO.UserDTO;
import UserBackend.mapper.UserMapper;
import UserBackend.service.ServiceDevice;
import UserBackend.service.ServiceMasterDeviceUser;
import UserBackend.service.ServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private ServiceUser serviceUser;
    @Autowired
    private ServiceDevice serviceDevice;
    @Autowired
    private ServiceMasterDeviceUser serviceMasterDeviceUser;
    public DeviceController(ServiceUser serviceUser, ServiceDevice serviceDevice, ServiceMasterDeviceUser serviceMasterDeviceUser) {
        this.serviceUser = serviceUser;
        this.serviceDevice = serviceDevice;
        this.serviceMasterDeviceUser = serviceMasterDeviceUser;
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam Long Id) {
        serviceMasterDeviceUser.deleteUser(Id);
    }
    @RequestMapping(value = "/deleteDevice", method = RequestMethod.DELETE)
    public void deleteDevice(@RequestParam Long Id, Long IdUser) {
        serviceMasterDeviceUser.deleteDevice(Id,IdUser);
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.PUT)
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void addUser(@RequestParam String username) {
        serviceUser.addUser(username);
    }
    @RequestMapping(value = "/addDevice", method = RequestMethod.PUT)
    public void addDevice(@RequestParam String description,String address, Long maximHrEnergyConsumption, Long userID) {
        serviceDevice.addDevice(description,address,maximHrEnergyConsumption, UserMapper.mapDtoToModel(serviceUser.findUserById(userID)));
    }
    @RequestMapping(value = "/deviceList", method = RequestMethod.GET)
    public List<DeviceDTO> getDevices (@RequestParam Long id){
        return serviceDevice.getAllDevicesForUser(UserMapper.mapDtoToModel(serviceUser.findUserById(id)));
    }

}
