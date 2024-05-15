package com.mygdx.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class ZOrderComponent implements Component, Pool.Poolable {

    // == attributes ==
    public int z = -1;

    // == public methods ==

    @Override
    public void reset() {
        z = -1;
    }
}
