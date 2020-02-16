package com.unfu.project.service.validation;

import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Pattern(regexp = "$(\\+?38)?([0-9]{3}(0-9){7})^", message = "Phone number is not valid")
public @interface Phone {
}
