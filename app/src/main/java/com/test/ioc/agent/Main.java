package com.test.ioc.agent;

import com.test.ioc.agent.dynamic.GamePlayerHandler;
import com.test.ioc.agent.statics.ProxyGamer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author cd5160866
 * @date 2019-07-06
 */
public class Main {

    public static void main(String[] args) {
        IGamer gamer = new RealPlayer();
        //no agent
        //        gamer.play();

        // static agent
//        IGamer gamerProxyStatic = new ProxyGamer(gamer);
//        gamerProxyStatic.play();

        // dynamic agent
        InvocationHandler invocationHandler = new GamePlayerHandler(gamer);
        ClassLoader classLoader = gamer.getClass().getClassLoader();
        IGamer gamerProxy = (IGamer) Proxy.newProxyInstance(classLoader, new Class[]{IGamer.class}, invocationHandler);
        gamerProxy.play();
    }
}
