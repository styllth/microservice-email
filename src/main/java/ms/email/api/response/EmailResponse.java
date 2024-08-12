package ms.email.api.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import ms.email.domain.enums.StatusEmail;

@Data
public class EmailResponse {

    private UUID emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "GMT-3")
    private LocalDateTime sendDateTimeEmail;
    private StatusEmail statusEmail;

}
