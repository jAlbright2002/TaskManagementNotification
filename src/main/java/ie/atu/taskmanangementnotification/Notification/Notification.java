package ie.atu.taskmanangementnotification.Notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    private String actionType;

    @NotEmpty
    private String message;

    private String dateOfAction;

    @NotEmpty
    private String email;

    @JsonProperty("isRead")
    private boolean isRead;
}
