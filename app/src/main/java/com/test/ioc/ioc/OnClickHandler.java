package com.test.ioc.ioc;

import android.app.Activity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cd5160866
 * @date 2019-07-06
 */
public class OnClickHandler implements InvocationHandler {

    private Activity activity;

    private Method method;


    public OnClickHandler(Activity activity, Method method) {
        this.activity = activity;
        this.method = method;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = this.method.invoke(activity,objects);
        return result;
    }
}
