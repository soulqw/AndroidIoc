package com.test.ioc.ioc;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cd5160866
 * @date 2019-07-06
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listenerTypeClz = View.OnClickListener.class, setListenerName = "setOnClickListener")
public @interface OnClick {
    int[] value();
}
