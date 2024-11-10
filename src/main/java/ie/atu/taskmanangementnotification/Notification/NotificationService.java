package ie.atu.taskmanangementnotification.Notification;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationDb notiDb;

    public NotificationService(NotificationDb notiDb) {
        this.notiDb = notiDb;
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

    public ResponseEntity<Notification> createNotification(Notification notif) {
        notif.setDateOfAction(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return ResponseEntity.ok(notiDb.save(notif));
    }

}
