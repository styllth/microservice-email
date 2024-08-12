package ms.email.common.validations.enums.patternvalidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

/*
  Usado para validar parte de um enum
 
  @EnumPatternValidation(regexp = "NEW|DEFAULT", message = "")
  private EnumType enum;

  o campo "message" e opcional

*/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumPatternValidator.class)
@ReportAsSingleViolation
public @interface EnumPatternValidation {

  String regexp();

  String message() default "Invalid value. Valid values are: {validValues}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
