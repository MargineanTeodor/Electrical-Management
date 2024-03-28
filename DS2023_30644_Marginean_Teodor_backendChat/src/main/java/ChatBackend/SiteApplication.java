package ChatBackend;


import ChatBackend.mapper.ChatMapper;
import ChatBackend.repository.ChatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
@Import(CorsConfig.class)
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ChatRepository chatRepository, ChatMapper chatMapper){
        return args -> {
            WebConfig webConfig =new WebConfig();
            webConfig.corsConfigurer();
        };
    }

}