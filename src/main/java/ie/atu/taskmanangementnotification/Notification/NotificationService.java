package ie.atu.taskmanangementnotification.Notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationDb notiDb;
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(NotificationDb notiDb, RabbitTemplate rabbitTemplate) {
        this.notiDb = notiDb;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<List<Notification>> getAllNotificationsForUser(String email) {
        Optional<List<Notification>> getAllNotifs = notiDb.findAllByEmail(email);
        if (getAllNotifs.isPresent()) {
            List<Notification> allNotifs = getAllNotifs.get();
            return ResponseEntity.ok(allNotifs);
        } else {
            List<Notification> empty = new ArrayList<>();
            return ResponseEntity.status(404).body(empty);
        }
    }

    @RabbitListener(queues = "regSendNotificationQueue")
    public void saveRegUserNotification(Notification notification) {
        notification.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        if (Objects.equals(notification.getActionType(), "SUCC_REG")) {
            notification.setMessage("Registered Successfully");
        } else if (Objects.equals(notification.getActionType(), "FAIL_REG")) {
            notification.setMessage("Registration failed as user exists");
        }
        notiDb.save(notification);
        System.out.println(notification);
        rabbitTemplate.convertAndSend("regRecNotificationQueue", notification);
    }

    @RabbitListener(queues = "logSendNotificationQueue")
    public void saveLogUserNotification(Notification notification) {
        notification.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        if (Objects.equals(notification.getActionType(), "SUCC_LOG")) {
            notification.setMessage("Logged in Successfully");
        } else if (Objects.equals(notification.getActionType(), "FAIL_LOG")) {
            notification.setMessage("Failed login attempt");
        }
        notiDb.save(notification);
        System.out.println(notification);
        rabbitTemplate.convertAndSend("logRecNotificationQueue", notification);
    }

    @RabbitListener(queues = "deleteTaskSendNotificationQueue")
    public void saveDeleteTaskNotification(Notification notification) {
        notification.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        notification.setMessage("Task deleted successfully");
        notiDb.save(notification);
        System.out.println(notification);
        rabbitTemplate.convertAndSend("deleteTaskRecNotificationQueue", notification);
    }

    @RabbitListener(queues = "updateTaskSendNotificationQueue")
    public void saveUpdateTaskNotification(Notification notification) {
        notification.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        notification.setMessage("Task updated successfully");
        notiDb.save(notification);
        System.out.println(notification);
        rabbitTemplate.convertAndSend("updateTaskRecNotificationQueue", notification);
    }

    @RabbitListener(queues = "createTaskSendNotificationQueue")
    public void saveCreateTaskNotification(Notification notification) {
        notification.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        notification.setMessage("Task created successfully");
        notiDb.save(notification);
        System.out.println(notification);
        rabbitTemplate.convertAndSend("createTaskRecNotificationQueue", notification);
    }
}
