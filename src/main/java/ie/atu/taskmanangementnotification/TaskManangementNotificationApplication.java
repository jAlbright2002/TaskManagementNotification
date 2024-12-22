package ie.atu.taskmanangementnotification;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class TaskManangementNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManangementNotificationApplication.class, args);
    }

}
