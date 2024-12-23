package ie.atu.taskmanangementnotification;

import ie.atu.taskmanangementnotification.Notification.Notification;
import ie.atu.taskmanangementnotification.Notification.NotificationController;
import ie.atu.taskmanangementnotification.Notification.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    void testGetAllNotifications() throws Exception {
        Notification notification = new Notification("123456", "CREATE_TASK", "Create task", "23-12-24", "james@atu.ie", true);
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);

        when(notificationService.getAllNotificationsForUser(notification.getEmail())).thenReturn(ResponseEntity.ok(notificationList));

        mockMvc.perform(get("/allNotifs/james@atu.ie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actionType").value(notification.getActionType()))
                .andExpect(jsonPath("$[0].message").value(notification.getMessage()))
                .andExpect(jsonPath("$[0].dateOfAction").value(notification.getDateOfAction()))
                .andExpect(jsonPath("$[0].email").value(notification.getEmail()))
                .andExpect(jsonPath("$[0].isRead").value(notification.isRead()));
    }

}
