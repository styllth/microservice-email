package ms.email.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable causa) {
        super(message, causa);
    }

}
