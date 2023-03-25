package com.space.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


import com.space.game.components.CollisionComponent;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.InputComponent;
import com.space.game.components.ParticleComponent;
import com.space.game.components.PositionComponent;
import com.space.game.components.PowerComponent;
import com.space.game.components.ScoreComponent;
import com.space.game.components.VelocityComponent;
import com.space.game.core.Entity;

public class ShipEntity extends Entity {

    private float thrust, velocityMax;
    private int offsetHitbox;
    private Vector2  dimension, spawnPosition;

    public ShipEntity(String name) {
        super(name);

        this.dimension = new Vector2(32,32);
        this.spawnPosition = new Vector2(100,10);
        this.velocityMax = 80.0f;
        this.thrust = 120.0f;
        this.offsetHitbox = 0;

        //Graphics
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("assets/ship1.png"));
        getComponent(GraphicsCompoment.class).setSizeX((int)dimension.x);
        getComponent(GraphicsCompoment.class).setSizeY((int)dimension.y);

        //Position
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(spawnPosition);
        //Velocity
        addComponent(new VelocityComponent());

        //Input
        addComponent(new InputComponent());
        getComponent(InputComponent.class).addInput(Input.Keys.W,new Vector2(0,1));
        getComponent(InputComponent.class).addInput(Input.Keys.S,new Vector2(0,-1));
        getComponent(InputComponent.class).addInput(Input.Keys.A,new Vector2(-1,0));
        getComponent(InputComponent.class).addInput(Input.Keys.D,new Vector2(1,0));
        getComponent(VelocityComponent.class).setActiveAcceleration(thrust);

        //Collision
        addComponent(new CollisionComponent());
                getComponent(CollisionComponent.class).setShapeRect(new Rectangle(spawnPosition.x
                ,spawnPosition.y,
                32,
                32));
        getComponent(CollisionComponent.class).setOffset(offsetHitbox);

        //Score
        addComponent(new ScoreComponent());
        getComponent(ScoreComponent.class).setScore(0);

        //Power
        addComponent(new PowerComponent());
        getComponent(PowerComponent.class).setPower("MainThruster", false);
        getComponent(PowerComponent.class).setTexture("MainThruster", new Texture("assets/ship2powered.png"));

        //particle
       /* addComponent(new ParticleComponent());
        getComponent(ParticleComponent.class).addEffect(Gdx.files.internal("assets/particle.red"), Gdx.files.internal("assets"));
        getComponent(ParticleComponent.class).getParticleEffectList().get(0).start();*/
    }
}
