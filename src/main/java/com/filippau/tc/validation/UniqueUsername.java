package com.filippau.tc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "{Пользователь уже есть}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
