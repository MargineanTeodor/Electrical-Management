package UserBackend.mapper;

import UserBackend.DTO.UserDTO;
import UserBackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public static UserDTO mapModelToDto(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setDeviceList(user.getListOfDevices());
        return userDTO;
    }
    public static User mapDtoToModel(UserDTO userDTO)
    {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setListOfDevices(userDTO.getDeviceList());
        return user;
    }
}
