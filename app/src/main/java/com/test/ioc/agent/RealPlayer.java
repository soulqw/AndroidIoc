package com.test.ioc.agent;

/**
 * @author cd5160866
 * @date 2019-07-06
 */
public class RealPlayer implements IGamer {
    @Override
    public void play() {
        System.out.println("真正的在玩游戏");
    }
}
