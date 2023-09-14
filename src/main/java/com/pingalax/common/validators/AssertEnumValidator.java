package com.pingalax.common.validators;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.pingalax.common.annotations.AsserEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhouxiaotao
 * @Description: 实现类
 * @date 2023-08-07 0:30
 */
public class AssertEnumValidator implements ConstraintValidator<AsserEnum, Object> {
    private List<String> values = new ArrayList();

    public AssertEnumValidator() {
    }

    public void initialize(AsserEnum constraintAnnotation) {
        Class<? extends BaseValidatorEnum> enumClass = constraintAnnotation.value();
        Object[] enumObjects = enumClass.getEnumConstants();
        Object[] var4 = enumObjects;
        int var5 = enumObjects.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Object enumObject = var4[var6];
            this.values.add(((BaseValidatorEnum)enumObject).getPrimary());
        }

    }

    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return Boolean.TRUE;
        } else if (ArrayUtil.isArray(value)) {
            Object[] valueObjs = ArrayUtil.wrap(value);
            Object[] var10 = valueObjs;
            int var11 = valueObjs.length;

            for(int var6 = 0; var6 < var11; ++var6) {
                Object valueObj = var10[var6];
                if (valueObj == null) {
                    return Boolean.FALSE;
                }

                if (!this.values.contains(String.valueOf(valueObj))) {
                    return Boolean.FALSE;
                }
            }

            return Boolean.TRUE;
        } else if (value instanceof Collection) {
            Collection<Object> valueObjs = (Collection)value;
            Iterator var4 = valueObjs.iterator();

            Object valueObj;
            do {
                if (!var4.hasNext()) {
                    return Boolean.TRUE;
                }

                valueObj = var4.next();
                if (valueObj == null) {
                    return Boolean.FALSE;
                }
            } while(this.values.contains(String.valueOf(valueObj)));

            return Boolean.FALSE;
        } else {
            String valueStr = String.valueOf(value);
            return StrUtil.isBlank(valueStr) ? Boolean.TRUE : this.values.contains(valueStr);
        }
    }
}