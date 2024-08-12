package ms.email.api.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorException {

  private Integer status;
  private OffsetDateTime dateTime;
  private String title;
  private List<Field> fields;
  private List<String> errors;

  public ErrorException(int status, String message) {
    this.status = status;
    this.dateTime = OffsetDateTime.now();
    this.title = message;
  }

  @AllArgsConstructor
  @Data
  public static class Field implements Serializable {

    private String field;
    private String message;
  }

}
