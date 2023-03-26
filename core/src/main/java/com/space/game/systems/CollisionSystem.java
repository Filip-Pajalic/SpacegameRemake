package com.space.game.systems;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.space.Constants;
import com.space.event.core.Event;
import com.space.event.core.EventTypes;
import com.space.event.core.Observer;
import com.space.event.core.Subject;
import com.space.event.events.DebugEvent;
import com.space.event.subjects.CollisionSubject;
import com.space.game.components.CollisionComponent;
import com.space.game.components.PositionComponent;
import com.space.game.entities.Entity;

import java.util.List;

public class CollisionSystem extends GameSystem {

    Event event_collision;
    private Subject subject;

    public CollisionSystem() {
        this.subject = new CollisionSubject();
        event_collision = new DebugEvent(EventTypes.DEBUG_COLLISION);
    }

    @Override
    public void update(List<Entity> entityList, float dt) {

        //reset collision
        for (Entity entity : entityList) {
            if (entity.getComponent(CollisionComponent.class) != null) {
                entity.getComponent(CollisionComponent.class).setHasCollided(false);
                entity.getComponent(CollisionComponent.class).clearCollisionList();
            }
        }

        for (Entity entity : entityList) {
            if (entity.getComponent(CollisionComponent.class) != null) {
                // update position
                if (entity.getComponent(PositionComponent.class) != null) {
                    entity.getComponent(CollisionComponent.class).setPosition(entity.getComponent(PositionComponent.class).getPosition());
                }
                for (Entity entity2 : entityList) {
                    if (entity2.getComponent(CollisionComponent.class) != null) {
                        if (!entity2.equals(entity)) {
                            CollisionComponent entityCollision = entity.getComponent(CollisionComponent.class);
                            CollisionComponent entityCollision2 = entity2.getComponent(CollisionComponent.class);
                            Rectangle rect = null;
                            Circle circle = null;
                            switch (entityCollision.getShape()) {
                                case Circle ignored -> circle = (Circle) entityCollision.getShape();
                                case Rectangle ignored -> rect = (Rectangle) entityCollision.getShape();
                                default ->
                                    throw new IllegalStateException("Unexpected value: " + entityCollision.getShape());
                            }
                            switch (entityCollision2.getShape()) {
                                case Circle ignored -> circle = (Circle) entityCollision2.getShape();
                                case Rectangle ignored -> rect = (Rectangle) entityCollision2.getShape();
                                default ->
                                    throw new IllegalStateException("Unexpected value: " + entityCollision.getShape());
                            }

/*                            if (entityCollision.getCircle() == null && entityCollision2.getCircle() != null) {
                                rect = new Rectangle(entityCollision.getPosition().x, entityCollision.getPosition().y, entityCollision.getRect().width, entityCollision.getRect().height);
                                circle = new Circle(entityCollision2.getPosition().x, entityCollision2.getPosition().y, entityCollision2.getCircle().radius);
                            } else if (entityCollision.getCircle() != null && entityCollision2.getCircle() == null) {
                                rect = new Rectangle(entityCollision2.getPosition().x, entityCollision2.getPosition().y, entityCollision2.getRect().width, entityCollision2.getRect().height);
                                circle = new Circle(entityCollision.getPosition().x, entityCollision.getPosition().y, entityCollision.getCircle().radius);
                            }*/
                            if (rect != null && circle != null) {
                                if (detectCollisionCircleRect(rect, circle)) {
                                    entityCollision.setHasCollided(true);
                                    entityCollision2.setHasCollided(true);
                                } else {
                                    entityCollision.setHasCollided(false);
                                    entityCollision2.setHasCollided(false);
                                }
                            }
                            if (Constants.DEBUGUITEXT) {
                                event_collision.setMessage("Collision: " + entityCollision.getHasCollided());
                                this.subject.notify(entity, event_collision);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addObserver(Observer ob) {
        this.subject.addObserver(ob);
    }

    public boolean detectCollisionCircleRect(Rectangle rect, Circle circle) {

        //Simple collision from https://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
        //Update this
        Vector2 circleDistance = new Vector2();
        float cornerDistance_sq = 0.0f;
        circleDistance.x = Math.abs(circle.x - rect.x);
        circleDistance.y = Math.abs(circle.y - rect.y);
        if (circleDistance.x > (rect.width / 2 + circle.radius)) {
            return false;
        }
        if (circleDistance.y > (rect.height / 2 + circle.radius)) {
            return false;
        }

        if (circleDistance.x <= (rect.width / 2)) {
            return true;
        }
        if (circleDistance.y <= (rect.height / 2)) {
            return true;
        }

        cornerDistance_sq = floatPow(circleDistance.x - rect.width / 2, 2.0f) + floatPow(circleDistance.y - rect.height / 2, 2.0f);

        return (cornerDistance_sq <= floatPow(circle.radius, 2.0f));
    }

    public float floatPow(float x, float p) {
        double dblResult = Math.pow(x, p);
        return (float) dblResult;
    }


}
