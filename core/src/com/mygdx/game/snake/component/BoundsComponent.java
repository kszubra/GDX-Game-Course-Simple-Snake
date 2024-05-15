package com.mygdx.game.snake.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class BoundsComponent implements Component, Pool.Poolable {

    // == attributes ==
    public final Rectangle rectangle = new Rectangle(0, 0, 1, 1);

    // == public methods ==
    @Override
    public void reset() {
        rectangle.setPosition(0, 0);
        rectangle.setSize(1, 1);
    }
}
