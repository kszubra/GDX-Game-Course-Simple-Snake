package com.mygdx.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class DimensionComponent implements Component, Pool.Poolable {

    // == attributes ==
    public float width = 1f;
    public float height = 1f;

    // == public methods ==
    @Override
    public void reset() {
        width = 1f;
        height = 1f;
    }
}
