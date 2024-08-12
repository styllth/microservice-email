package ms.email.common.validations.enums.namevalidator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValidation, String> {

  private EnumValidation annotation;
  private List<String> validValues;

  @Override
  public void initialize(EnumValidation constraintAnnotation) {
    this.annotation = constraintAnnotation;
    this.validValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
        .map(Enum::name)
        .collect(Collectors.toList());

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || !validValues.contains(value)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          annotation.message().replace("{validValues}", String.join(", ",
              validValues)))
          .addConstraintViolation();
      return false;
    }
    return true;
  }

}
