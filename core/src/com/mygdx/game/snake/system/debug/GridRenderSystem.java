package com.mygdx.game.snake.system.debug;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.snake.util.ViewportUtils;

public class GridRenderSystem extends EntitySystem {

    // == attributes ==
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // == constructors ==
    public GridRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        this.viewport = viewport;
        this.renderer = renderer;
    }

    // == update ==
    @Override
    public void update(float deltaTime) {
        viewport.apply();
        ViewportUtils.drawGrid(viewport, renderer);
    }
}
