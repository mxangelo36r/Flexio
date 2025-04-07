//package learn.app.validations;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import learn.app.validations.*;
//
//@Constraint(validatedBy = UniqueExerciseNameValidator.class)
//@Target({ ElementType.TYPE })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface UniqueExerciseName {
//    String message() default "Duplicate exercise name on the same day";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}