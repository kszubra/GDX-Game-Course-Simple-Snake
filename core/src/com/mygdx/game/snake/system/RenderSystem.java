package com.mygdx.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.snake.component.DimensionComponent;
import com.mygdx.game.snake.component.PositionComponent;
import com.mygdx.game.snake.component.TextureComponent;
import com.mygdx.game.snake.component.ZOrderComponent;
import com.mygdx.game.snake.util.Mappers;
import com.mygdx.game.snake.util.ZOrderComparator;

public class RenderSystem extends SortedIteratingSystem {

    // == constants ==
    private static final Family FAMILY = Family.all(
            PositionComponent.class,
            DimensionComponent.class,
            TextureComponent.class,
            ZOrderComponent.class
    ).get();

    // == attributes ==
    private final SpriteBatch batch;
    private final Viewport viewport;

    // == constructors ==
    public RenderSystem(SpriteBatch batch, Viewport viewport) {
        super(FAMILY, ZOrderComparator.INSTANCE);
        this.batch = batch;
        this.viewport = viewport;
    }

    // == process ==
    @Override
    public void update(float deltaTime) {

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // NOTE: calls processEntity for every entity in family inside for loop
        super.update(deltaTime);

        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        TextureComponent texture = Mappers.TEXTURE.get(entity);

        batch.draw(texture.region,
                position.x, position.y,
                dimension.width, dimension.height
        );
    }
}
