package UserBackend.service;

import UserBackend.DTO.UserDTO;
import UserBackend.mapper.UserMapper;
import UserBackend.model.Device;
import UserBackend.model.User;
import UserBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceUser {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public ServiceUser(UserRepository userRepository, UserMapper userMapper)
    {
        this.userRepository= userRepository;
        this.userMapper = userMapper;
    }
    public UserDTO findUserByUsername(String username)
    {
        User userFounded = userRepository.findFirstByUsername(username);
        UserDTO userFoundedDTO = userMapper.mapModelToDto(userFounded);
        return userFoundedDTO;
    }
    public UserDTO findUserById(Long id)
    {
        User userFounded = userRepository.findUserById(id);
        UserDTO userFoundedDTO = userMapper.mapModelToDto(userFounded);
        return userFoundedDTO;
    }
    public List<UserDTO> findAllUsers()
    {
        List<User> listOfUsers = userRepository.findAll();
        List<UserDTO> DTOList = new ArrayList<UserDTO>();
        for (User user: listOfUsers) {
            DTOList.add(userMapper.mapModelToDto(user));
        }
        return DTOList;
    }
    public void addUser(String username) {

        User user =new User();
        user.setUsername(username);
        user.setListOfDevices(new ArrayList<Device>());
        userRepository.save(user);
    }
}
