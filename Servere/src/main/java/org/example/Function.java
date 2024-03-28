package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Function implements Runnable{
    int time;
    int deviceId;

    public Function(int deviceId, int time) {
        this.time = time;
        this.deviceId = deviceId;
    }

    public void Task(int time, int deviceId) {
        String csvFile = "./sensor.csv";
        float[] citire= new float[6];
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((citire[0] = Float.parseFloat(br.readLine())) != -1) {
                int sem=1;
                while(sem<=5)
                {
                    Thread.sleep(time);
                    citire[sem] = Float.parseFloat(br.readLine());
                    sem++;
                }
                float average=0;
                for(int i=0;i<=4;i++)
                {
                    average+= citire[i+1]-citire[i];
                }
                average= average/6;
                currentDateTime = currentDateTime.plus(Duration.ofMinutes(60));
                MessageType messageType= new MessageType(average,currentDateTime.format(formatter),deviceId);
                String message = new Gson().toJson(messageType);
                sendFc(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        this.Task(time,deviceId);
        System.out.println(time);
    }

    public void sendFc(String message ) throws URISyntaxException, NoSuchAlgorithmException, KeyManagementException {
        ConnectionFactory factory = new ConnectionFactory();
        String QUEUE_NAME = "RabbitMQ";
        factory.setUri("amqps://rzbujrxp:iVXgJ2zl6adaApNWjRjoPved-6xeGjsk@rat.rmq2.cloudamqp.com/rzbujrxp");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare a queue for messages
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Publish a message to the queue
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" Message sent : '" + message + "'");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
