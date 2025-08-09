package com.bancoacme.servicolistas.infrastructure.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogTempoExecucao {
    String value() default "";
}
