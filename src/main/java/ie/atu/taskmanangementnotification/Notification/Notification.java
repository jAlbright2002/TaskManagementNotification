package ie.atu.taskmanangementnotification.Notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "notifications")
public class Notification {

    @Id
    private String id;

    @NotEmpty
    private String actionType;

    @NotEmpty
    private String message;

    private String dateOfAction;

    @Email
    private String email;

    @JsonProperty("isRead")
    private boolean isRead;
}
