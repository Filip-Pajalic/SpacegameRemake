package com.space.game.systems;

import com.badlogic.gdx.math.Circle;
import com.space.event.core.Event;
import com.space.event.core.EventTypes;
import com.space.event.core.Observer;
import com.space.event.core.Subject;
import com.space.event.events.HealthEvent;
import com.space.event.events.ScoringEvent;
import com.space.event.subjects.ScoringSubject;
import com.space.game.components.CollisionComponent;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.HealthComponent;
import com.space.game.components.ScoreComponent;
import com.space.game.entities.Entity;
import com.space.game.entities.ShipEntity;
import com.space.game.entities.TargetEntity;

import java.util.List;

public class ScoringSystem extends GameSystem {

    private Subject subject;
    private Event scoringEvent;
    private Event healthEvent;
    private Float timer = 0.0f;

    public ScoringSystem() {
        subject = new ScoringSubject();
        scoringEvent = new ScoringEvent(EventTypes.SCORING_EVENT);
        healthEvent = new HealthEvent(EventTypes.HEALTH_EVENT);
    }

    @Override
    public void addObserver(Observer ob) {
        this.subject.addObserver(ob);
    }

    /* fix logic here should update health reglardles of graphics*/
    @Override
    public void update(List<Entity> entityList, float dt) {
        for (Entity entity : entityList) {
            if (entity.getComponent(CollisionComponent.class) != null) {
                for (Entity entity2 : entityList) {
                    if (entity2.getComponent(HealthComponent.class) != null && entity2.getComponent(GraphicsCompoment.class) != null && entity2.getComponent(CollisionComponent.class) != null) {
                        if (entity2 instanceof TargetEntity) {
                            if (entity instanceof ShipEntity) {
                                updateTarget((TargetEntity) entity2, (ShipEntity) entity, dt);
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateTarget(TargetEntity targetEntity, ShipEntity entity, float dt) {
        /*circle radius not being updated after score 0 properly*/
        int health = targetEntity.getComponent(HealthComponent.class).getHealth();
        updateHealth(targetEntity, health);
        if (targetEntity.getComponent(CollisionComponent.class).getHasCollided()) {
            timer += dt;
            if (timer >= 0.05) {
                timer -= 0.05f;
                targetEntity.getComponent(HealthComponent.class).removeHealth(1);
            }

        }
        if (health == 0) {
            updateScore(entity);
            targetEntity.randomizeProperties();
        } else {
            targetEntity.getComponent(GraphicsCompoment.class).setSizeX(health + 15);
            targetEntity.getComponent(CollisionComponent.class).setShape(new Circle(20, 20, health + 5));
        }


    }

    public void updateScore(ShipEntity entity) {
        entity.getComponent(ScoreComponent.class).updateScore(1);
        scoringEvent.setMessage(String.valueOf(entity.getComponent(ScoreComponent.class).getScore()));
        this.subject.notify(entity, scoringEvent);
    }

    public void updateHealth(TargetEntity targetEntity, int health) {
        healthEvent.setMessage(String.valueOf(health));
        this.subject.notify(targetEntity, healthEvent);
    }

}
