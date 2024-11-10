package ie.atu.taskmanangementnotification.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationDb extends MongoRepository<Notification, String> {

    Optional<List<Notification>> findAllByEmail(String email);

}
