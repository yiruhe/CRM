package com.it.crm.util.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *  标记目标方法是否需要权限
 * @author Q
 * @version V1.0
 * @Description: ${todo}
 * @date 2017-12-11 12:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {

    String value();
}
