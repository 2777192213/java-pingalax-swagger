package com.pingalax.common.annotations;

import com.pingalax.common.interfaces.BaseValidatorEnum;
import com.pingalax.common.validators.AssertEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouxiaotao
 * @Description: 校验基础注解
 * @date 2023-08-07 0:18
 */
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {AssertEnumValidator.class}
)
public @interface AsserEnum {
    Class<? extends BaseValidatorEnum> value();

    String message() default "数据值不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
