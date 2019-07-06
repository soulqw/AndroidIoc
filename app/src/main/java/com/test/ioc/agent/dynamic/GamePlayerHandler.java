package com.test.ioc.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cd5160866
 * @date 2019-07-06
 */
public class GamePlayerHandler implements InvocationHandler {

    Object object;

    public GamePlayerHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = method.invoke(object, objects);
        return result;
    }
}
