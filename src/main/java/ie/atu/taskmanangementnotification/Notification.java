package ie.atu.taskmanangementnotification;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private String actionType;

    @NotEmpty
    private String message;

    @NotEmpty
    private Date dateOfAction;

    @NotEmpty
    private String email;

    private boolean isRead;
}
