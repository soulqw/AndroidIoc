package com.qw;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cd5160866
 * @date 2019-07-09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface HelloWorld {
}
