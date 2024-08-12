package ms.email.api.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import ms.email.domain.exceptions.EmailException;
import ms.email.domain.exceptions.ResourceException;
import ms.email.domain.exceptions.ResourceNotFoundException;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    List<ErrorException.Field> fields = new ArrayList<>();

    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
      String field = ((FieldError) error).getField();
      String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
      fields.add(new ErrorException.Field(field, message));
    }

    ErrorException errorException = new ErrorException(
        status.value(),
        OffsetDateTime.now(),
        "Um ou mais campos estão inválidos.",
        fields,
        null);

    return handleExceptionInternal(ex, errorException, headers, status, request);
  }

  @ExceptionHandler(ResourceException.class)
  public ResponseEntity<Object> handleResourceException(
      ResourceException ex,
      WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    return handleExceptionInternal(
        ex,
        new ErrorException(status.value(), ex.getMessage()),
        new HttpHeaders(),
        status,
        request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException ex,
      WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    return handleExceptionInternal(
        ex,
        new ErrorException(status.value(), ex.getMessage()),
        new HttpHeaders(),
        status,
        request);
  }

  @ExceptionHandler(EmailException.class)
  public ResponseEntity<Object> handleEmailException(
      EmailException ex,
      WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    return handleExceptionInternal(
        ex,
        new ErrorException(status.value(), ex.getMessage()),
        new HttpHeaders(),
        status,
        request);
  }

}
