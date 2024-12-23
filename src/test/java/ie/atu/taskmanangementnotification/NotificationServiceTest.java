package ie.atu.taskmanangementnotification;

import ie.atu.taskmanangementnotification.Notification.Notification;
import ie.atu.taskmanangementnotification.Notification.NotificationDb;
import ie.atu.taskmanangementnotification.Notification.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationDb notiDb;

    @Mock
    private RabbitTemplate rabbitTemplate;

    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification("123456", "CREATE_TASK", "Updated Task", "23-12-24", "james@atu.ie", true);
    }

    @Test
    public void testGetAllNotificationsForUnknownUser() {
        when(notiDb.findAllByEmail("unknown@atu.ie")).thenReturn(Optional.empty());

        ResponseEntity<List<Notification>> response = notificationService.getAllNotificationsForUser("unknown@atu.ie");

        assertEquals(404, response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testSaveRegUserNotificationSuccess() {
        notification.setActionType("SUCC_REG");

        notificationService.saveRegUserNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("regRecNotificationQueue", notification);
    }

    @Test
    public void testSaveRegUserNotificationFail() {
        notification.setActionType("FAIL_REG");

        notificationService.saveRegUserNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("regRecNotificationQueue", notification);
    }

    @Test
    public void testSaveLogUserNotificationSuccess() {
        notification.setActionType("SUCC_LOG");

        notificationService.saveLogUserNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("logRecNotificationQueue", notification);
    }

    @Test
    public void testSaveLogUserNotificationFail() {
        notification.setActionType("FAIL_LOG");

        notificationService.saveLogUserNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("logRecNotificationQueue", notification);
    }

    @Test
    public void testSaveDeleteTaskNotification() {
        notification.setActionType("DELETE_TASK");

        notificationService.saveDeleteTaskNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("deleteTaskRecNotificationQueue", notification);
    }

    @Test
    public void testSaveUpdateTaskNotification() {
        notification.setActionType("UPDATE_TASK");

        notificationService.saveUpdateTaskNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("updateTaskRecNotificationQueue", notification);
    }

    @Test
    public void testSaveCreateTaskNotification() {
        notification.setActionType("CREATE_TASK");

        notificationService.saveCreateTaskNotification(notification);

        verify(notiDb, times(1)).save(notification);
        verify(rabbitTemplate, times(1)).convertAndSend("createTaskRecNotificationQueue", notification);
    }
}
