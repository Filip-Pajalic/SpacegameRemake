package com.space.game.components;

import com.badlogic.gdx.math.Vector2;

import com.space.game.core.Component;

public class VelocityComponent extends Component {

    private Vector2 velocity = new Vector2(0f,0f);
    private Vector2 acceleration = new Vector2(0f,0f);
    private Vector2 accelerationChange = new Vector2(0f,0f);
    private Vector2 activeAcceleration = new Vector2(0f,0f);

    @Override
    public void update(float dt) {

    }

    public void start(){

    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAccelerationChangeX(float velocityX) {
        this.accelerationChange.x = velocityX;
    }
    public void setAccelerationChangeY(float velocityY) {
        this.accelerationChange.y = velocityY;
    }

    public Vector2 getAccelerationChange() {
        return accelerationChange;
    }

    public void setAccelerationChange(Vector2 accelerationChange) {
        this.accelerationChange = accelerationChange;
    }

    public void setActiveAcceleration(float activeAcceleration) {
        this.activeAcceleration.x = activeAcceleration;
        this.activeAcceleration.y = activeAcceleration;
    }

    public Vector2 getActiveAcceleration() {
        return activeAcceleration;
    }

}
