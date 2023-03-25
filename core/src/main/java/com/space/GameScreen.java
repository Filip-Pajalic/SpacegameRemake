package com.space;


import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.space.event.core.Observer;
import com.space.event.observers.DebugObserver;
import com.space.game.components.GraphicsCompoment;
import com.space.game.core.Entity;
import com.space.game.entities.ShipEntity;
import com.space.game.entities.TargetEntity;
import com.space.game.systems.*;
import com.space.gui.GameUi;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class GameScreen implements Screen {

    //world parameters

    private float gravityConstant = 60.1f;
    private boolean isRunning = false;
    private boolean debug;
    private List<Entity> entityList = new ArrayList<>();
    private Entity entity;
    private PhysicsSystem physicsSystem;
    private RenderSystem renderSystem;
    private MovableSystem movableSystem;
    private CollisionSystem collisionSystem;
    private ScoringSystem scoringSystem;
    private Observer debugObserver;
    private GameUi gameUi;

    GameScreen(){
        //this.gameUi = new GameUi(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        entity = new ShipEntity("ship");
        addGameObjectToScreen(this.entity);
        entity = new TargetEntity("Circle" + UUID.randomUUID());
        entity.getComponent(GraphicsCompoment.class).setShape(GraphicsCompoment.Shapes.CIRCLE);
        addGameObjectToScreen(this.entity);
        collisionSystem = new CollisionSystem();
        physicsSystem = new PhysicsSystem(gravityConstant,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderSystem = new RenderSystem(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        movableSystem = new MovableSystem();
        debugObserver = new DebugObserver();
        scoringSystem = new ScoringSystem();
/*        renderSystem.setGameUi(this.gameUi);
        physicsSystem.addObserver(this.gameUi);
        collisionSystem.addObserver(this.gameUi);
        scoringSystem.addObserver(this.gameUi);*/
        start();
    }

    public void start(){
        if(!isRunning){
            for(Entity entity : entityList){
                entity.start();
            }
        }
    }

    @Override
    public void render(float deltaTime) {
        this.isRunning = true;
        for(Entity entity : this.entityList){
            entity.update(deltaTime);
        }
        renderSystem.update(entityList,deltaTime);
        physicsSystem.update(entityList,deltaTime);
        movableSystem.update(entityList,deltaTime);
        collisionSystem.update(entityList,deltaTime);
        scoringSystem.update(entityList,deltaTime);

    }

    public void addGameObjectToScreen(Entity entity){
        if(!isRunning){
            entityList.add(entity);
        }
        else{
            entityList.add(entity);
            entity.start();
        }
    }

    @Override
    public void resize(int width, int height) {
        renderSystem.getViewport().update(width,height,true);
        renderSystem.getBatch().setProjectionMatrix(renderSystem.getCamera().combined);
        renderSystem.getShapeRenderer().setProjectionMatrix(renderSystem.getCamera().combined);
       // this.gameUi.setViewPort(renderSystem.getViewport());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //font.dispose();
        renderSystem.dispose();
    }
    @Override
    public void show() {

    }
}
