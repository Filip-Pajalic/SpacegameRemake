package com.space.game.components;

import com.badlogic.gdx.math.Vector2;
import com.space.game.components.external.Component;

public class PositionComponent implements Component {

    private Vector2 position = new Vector2(0f, 0f);
    private Vector2 lastPosition = new Vector2(0f, 0f);


    @Override
    public void update(float dt) {

    }

    public void start() {

    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void translate(float xChange, float yChange) {
        this.position.x = this.position.x + xChange;
        this.position.y = this.position.y + yChange;
    }

    public void setLastPosition(Vector2 position) {
        this.lastPosition = position;
    }

    public Vector2 getLastPosition() {
        return this.lastPosition;
    }


}
