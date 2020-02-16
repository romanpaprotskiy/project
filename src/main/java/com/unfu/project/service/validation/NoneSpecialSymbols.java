package com.unfu.project.service.validation;

import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Pattern(regexp = "[,;^%$#!&?()/]", message = "Can not contain special symbols")
public @interface NoneSpecialSymbols {
}
