package ms.email.common.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

  public static String formatDatePtBr(OffsetDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));
    return formatter.format(date);
  }

  public static String formatDateTimePtBr(OffsetDateTime date) {
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
    return formatter.format(date);
  }

}
