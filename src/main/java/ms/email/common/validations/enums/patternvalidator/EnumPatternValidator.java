package ms.email.common.validations.enums.patternvalidator;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumPatternValidator implements ConstraintValidator<EnumPatternValidation, Enum<?>> {

  private EnumPatternValidation annotation;
  private Pattern pattern;

  @Override
  public void initialize(EnumPatternValidation constraintAnnotation) {
    try {
      this.annotation = constraintAnnotation;
      pattern = Pattern.compile(constraintAnnotation.regexp());
    } catch (PatternSyntaxException e) {
      throw new IllegalArgumentException("Given regex is invalid", e);
    }
  }

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    if (value == null || !pattern.matcher(value.name()).matches()) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          annotation.message().replace("{validValues}", annotation.regexp().replace("|", ", ")))
          .addConstraintViolation();
      return false;

    }
    return true;
  }
}
