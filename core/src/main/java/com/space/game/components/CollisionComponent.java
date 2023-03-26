package com.space.game.components;

import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.space.game.components.external.Component;

import java.util.ArrayList;
import java.util.List;


public class CollisionComponent<T extends Shape2D> implements Component {

    private T shape2D;

    private boolean isEnabled;
    private boolean hasCollided;
    private int hitBoxOffset = 0;
    private List<String> collisionList;
    private Vector2 position;


    public CollisionComponent(T shape2D) {
        this.shape2D = shape2D;
        isEnabled = true;
        hasCollided = false;
        hitBoxOffset = 0;
        collisionList = new ArrayList<>();
        position = new Vector2(0, 0);
    }

    public void setShape(T shape2D) {
        this.shape2D = shape2D;
    }

    public T getShape() {
        return shape2D;
    }


    @Override
    public void update(float dt) {
    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return isEnabled;
    }


    public void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }

    public boolean getHasCollided() {
        return this.hasCollided;
    }

    public void addToCollisionList(String name) {
        collisionList.add(name);
    }

    public boolean isInCollisionList(String name) {
        for (String collisionName : collisionList) {
            if (collisionName.equals(name))
                return true;
        }
        return false;
    }

    public void clearCollisionList() {
        collisionList = new ArrayList<>();
    }

    public int getHitBoxOffset() {
        return hitBoxOffset;
    }

    public void setHitBoxOffset(int hitBoxOffset) {
        this.hitBoxOffset = hitBoxOffset;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position.x = position.x + hitBoxOffset;
        this.position.y = position.y + hitBoxOffset;
    }


}
