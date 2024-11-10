package ie.atu.taskmanangementnotification.Notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notiService;

    public NotificationController(NotificationService notiService) {
        this.notiService = notiService;
    }

    @GetMapping("/allNotifs/{email}")
    public ResponseEntity<List<Notification>> getAllNotificationsForUser(@PathVariable String email) {
        return notiService.getAllNotificationsForUser(email);
    }

    @PostMapping("/createNotification")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notif) {
        return notiService.createNotification(notif);
    }

}
