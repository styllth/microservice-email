package ms.email.common.validations.arrays;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArrayValidator implements ConstraintValidator<ArrayValidation, Object> {

  private ArrayValidation annotation;

  @Override
  public void initialize(ArrayValidation constraintAnnotation) {
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {

    if (value instanceof Collection<?>) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
        annotation.message())
        .addConstraintViolation();
    return false;
  }

}
