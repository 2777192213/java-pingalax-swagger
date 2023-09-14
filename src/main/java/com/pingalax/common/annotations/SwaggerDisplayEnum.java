package com.pingalax.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouxiaotao
 * @Description: 枚举注解
 * @date 2023-08-07 0:37
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerDisplayEnum {
    String id() default "code";

    String name() default "desc";
}
