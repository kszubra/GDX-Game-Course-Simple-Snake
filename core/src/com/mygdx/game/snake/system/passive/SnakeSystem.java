package com.mygdx.game.snake.system.passive;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.snake.component.SnakeComponent;
import com.mygdx.game.snake.util.Mappers;


public class SnakeSystem extends EntitySystem implements EntityListener {

    // == constants ==
    private static final Logger log = new Logger(SnakeSystem.class.getSimpleName(), Logger.DEBUG);
    private static final Family FAMILY = Family.all(SnakeComponent.class).get();

    // == public methods ==
    @Override
    public boolean checkProcessing() {
        // returning false "makes" this system "passive" e.g. update method will NOT be called
        return false;
    }

    @Override
    public void update(float deltaTime) {
        // NOT PROCESSING ANYTHING!!!
    }

    @Override
    public void addedToEngine(Engine engine) {
        log.debug("SnakeSystem addedToEngine adding listener");
        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        log.debug("SnakeSystem removedFrom Engine");
        engine.removeEntityListener(this);
    }

    @Override
    public void entityAdded(Entity entity) {
        log.debug("entityAdded entity= " + entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        log.debug("entityRemoved entity= " + entity);
        SnakeComponent snake = Mappers.SNAKE.get(entity);

        Engine engine = getEngine();
        engine.removeEntity(snake.head);

        for(Entity bodyPart : snake.bodyParts) {
            engine.removeEntity(bodyPart);
        }
    }
}
