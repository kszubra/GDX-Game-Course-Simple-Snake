package com.jga.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.snake.component.DirectionComponent;
import com.mygdx.game.snake.component.MovementComponent;
import com.mygdx.game.snake.config.GameConfig;
import com.mygdx.game.snake.util.Mappers;

public class DirectionSystem extends IteratingSystem {

    // == constants ==
    private static final Family FAMILY = Family.all(
            DirectionComponent.class,
            MovementComponent.class
    ).get();

    // == constructors ==
    public DirectionSystem() {
        super(FAMILY);
    }

    // == process ==

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DirectionComponent direction = Mappers.DIRECTION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        // reset speed to 0
        movement.xSpeed = 0f;
        movement.ySpeed = 0f;

        // based on direction set speed
        if (direction.isRight()) {
            movement.xSpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isLeft()) {
            movement.xSpeed = -GameConfig.SNAKE_SPEED;
        } else if (direction.isUp()) {
            movement.ySpeed = GameConfig.SNAKE_SPEED;
        } else if (direction.isDown()) {
            movement.ySpeed = -GameConfig.SNAKE_SPEED;
        }
    }
}
