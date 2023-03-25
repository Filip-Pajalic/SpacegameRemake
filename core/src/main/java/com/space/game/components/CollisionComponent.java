package com.space.game.components;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.space.game.core.Component;


import java.util.ArrayList;
import java.util.List;



public class CollisionComponent extends Component {

    private Rectangle rect = null;
    private Circle circle = null;
    private boolean collided = false;
    private int offset = 0;
    private List<String> collisionList = new ArrayList<>();
    private final Vector2 position = new Vector2(0,0);

    @Override
    public void update(float dt) {
    }

    public void setShapeCircle(Circle circle){
        this.circle = circle;
        this.position.x = circle.x;
        this.rect = null;
    }

    public void setShapeRect(Rectangle rect){
        this.rect  = rect;
        this.position.x = rect.x;
        this.position.y = rect.y;
        this.circle = null;
    }

    public Rectangle getRect(){
        return this.rect;
    }

    public Circle getCircle(){
        return this.circle;
    }
    public void setCollided(boolean collided){
        this.collided = collided;
    }
    public boolean getCollided(){
        return this.collided;
    }

    public void addToCollisionList(String name){
        collisionList.add(name);
    }

    public boolean isInCollisionList(String name){
        for (String collisionName: collisionList) {
            if(collisionName.equals(name))
                return true;
        }
        return false;
    }

    public void clearCollisionList(){
        collisionList = new ArrayList<>();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position.x = position.x + offset;
        this.position.y = position.y + offset;
    }
}
