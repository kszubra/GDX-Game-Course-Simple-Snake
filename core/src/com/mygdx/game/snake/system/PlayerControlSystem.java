package com.mygdx.game.snake.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.snake.common.Direction;
import com.mygdx.game.snake.component.DirectionComponent;
import com.mygdx.game.snake.component.PlayerComponent;
import com.mygdx.game.snake.util.Mappers;


public class PlayerControlSystem extends IteratingSystem {

    // == constants ==
    private static final Family FAMILY = Family.all(
            PlayerComponent.class,
            DirectionComponent.class
    ).get();

    // == constructors ==
    public PlayerControlSystem() {
        super(FAMILY);
    }

    // == process ==
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        DirectionComponent directionComponent = Mappers.DIRECTION.get(entity);

        if(leftPressed) {
            updateDirection(directionComponent, Direction.LEFT);
        } else if(rightPressed) {
            updateDirection(directionComponent, Direction.RIGHT);
        } else if(upPressed) {
            updateDirection(directionComponent, Direction.UP);
        } else if(downPressed) {
            updateDirection(directionComponent, Direction.DOWN);
        }
    }

    // == private methods ==
    private void updateDirection(DirectionComponent directionComponent, Direction newDirection) {
        if(!directionComponent.isOpposite(newDirection)) {
            directionComponent.direction = newDirection;
        }
    }
}
