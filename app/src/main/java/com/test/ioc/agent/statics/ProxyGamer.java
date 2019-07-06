package com.test.ioc.agent.statics;

import com.test.ioc.agent.IGamer;

/**
 * @author cd5160866
 * @date 2019-07-06
 */
public class ProxyGamer implements IGamer {
    private IGamer gamer;

    public ProxyGamer(IGamer gamer) {
        this.gamer = gamer;
    }

    @Override
    public void play() {
        this.gamer.play();
    }
}
