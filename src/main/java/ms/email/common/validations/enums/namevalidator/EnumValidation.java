package ms.email.common.validations.enums.namevalidator;

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

  Usado para validar todo o enum
 
  @EnumValidation(enumClass = CustomerType.class, message= "Somente os valores [{validValues}] são aceitos.")
  private String customerTypeString;

  o campo "message" e opcional

*/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidator.class)
@ReportAsSingleViolation
public @interface EnumValidation {

  Class<? extends Enum<?>> enumClass();

  String message() default "Invalid value. Value must be one of [{validValues}]";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
