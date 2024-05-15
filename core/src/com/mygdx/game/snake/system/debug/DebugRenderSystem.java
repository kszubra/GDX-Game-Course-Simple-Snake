package com.mygdx.game.snake.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.snake.component.BoundsComponent;
import com.mygdx.game.snake.util.Mappers;

public class DebugRenderSystem extends IteratingSystem {

    // == constants ==
    private static final Family FAMILY = Family.all(
            BoundsComponent.class
    ).get();

    // == attributes ==
    private final Viewport viewport;
    private final ShapeRenderer renderer;

    // == constructors ==
    public DebugRenderSystem(Viewport viewport, ShapeRenderer renderer) {
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor().cpy();

        viewport.apply();
        renderer.setColor(Color.RED);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        // calls processEntity internally in for loop
        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundsComponent bounds = Mappers.BOUNDS.get(entity);

        renderer.rect(bounds.rectangle.x, bounds.rectangle.y,
                bounds.rectangle.width, bounds.rectangle.height);
    }
}
