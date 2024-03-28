package UserBackend;

import UserBackend.mapper.DeviceMapper;
import UserBackend.mapper.UserMapper;
import UserBackend.repository.DeviceRepository;
import UserBackend.repository.UserRepository;
import UserBackend.service.ServiceDevice;
import UserBackend.service.ServiceUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, UserMapper userMapper, DeviceRepository deviceRepository, DeviceMapper deviceMapper){
        return args -> {
            WebConfig webConfig =new WebConfig();
            webConfig.corsConfigurer();
            ServiceUser serviceUser = new ServiceUser(userRepository,userMapper);
            ServiceDevice serviceDevice = new ServiceDevice(deviceRepository,deviceMapper);
            serviceUser.addUser("admin");
        };
    }

}