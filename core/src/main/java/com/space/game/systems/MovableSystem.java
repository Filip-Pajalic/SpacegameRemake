package com.space.game.systems;

import com.badlogic.gdx.math.Vector2;
import com.space.event.core.Observer;
import com.space.game.components.InputComponent;
import com.space.game.components.VelocityComponent;
import com.space.game.entities.Entity;

import java.util.List;

public class MovableSystem extends GameSystem {

    @Override
    public void update(List<Entity> entityList, float dt) {
        for (Entity entity : entityList) {
            if (entity.getComponent(InputComponent.class) != null && entity.getComponent(VelocityComponent.class) != null) {
                Vector2 direction = entity.getComponent(InputComponent.class).getDirection();
                Vector2 acceleration = new Vector2(entity.getComponent(VelocityComponent.class).getActiveAcceleration());
                entity.getComponent(VelocityComponent.class).setAccelerationChange(acceleration.scl(direction));
            }
        }
    }

    @Override
    public void addObserver(Observer ob) {
    }

    public MovableSystem() {
    }
}
