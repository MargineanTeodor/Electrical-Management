package UserBackend;

import UserBackend.mapper.SensorMapper;
import UserBackend.service.repository.SensorRepository;
import UserBackend.service.ServiceSensor;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.rabbitmq.client.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
@Import(CorsConfig.class)
public class SiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteApplication.class, args);
    }

    @Bean
    CommandLineRunner init(SensorRepository sensorRepository, SensorMapper sensorMapper){
        return args -> {
            WebConfig webConfig =new WebConfig();
            webConfig.corsConfigurer();
            ServiceSensor serviceSensor = new ServiceSensor(sensorRepository,sensorMapper);
            ConnectionFactory factory = new ConnectionFactory();
            String QUEUE_NAME = "RabbitMQ";

            factory.setUri("amqps://rzbujrxp:iVXgJ2zl6adaApNWjRjoPved-6xeGjsk@rat.rmq2.cloudamqp.com/rzbujrxp");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();

                String timestampString = jsonObject.get("timestamp").getAsString();
                Long deviceId = jsonObject.get("device_id").getAsLong();
                float measurementValue = jsonObject.get("measurement_value").getAsFloat();
                // Parse the timestamp string into a Date object
                if (measurementValue>12)
                {

                }
                Date timestamp = null;
                Date timestamp2 = null;
                try {
                    timestamp = formatter.parse(timestampString);
                    timestamp2 = formatter2.parse(timestampString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                // Use a Calendar to extract the hour
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(timestamp);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                serviceSensor.addSensor(hour,timestamp2,deviceId,measurementValue);
            };

            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

        };
    }

}