package ie.atu.taskmanangementnotification;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationDb extends MongoRepository<Notification, String> {
}
