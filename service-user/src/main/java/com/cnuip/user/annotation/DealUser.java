package com.cnuip.user.annotation;

import java.lang.annotation.*;

/**
 * Created by Crixalis on 2018/4/11.
 */
@Target(ElementType.METHOD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DealUser {
}
