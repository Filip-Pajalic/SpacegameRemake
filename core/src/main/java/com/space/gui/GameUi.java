package com.space.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.space.Constants;
import com.space.event.core.Event;
import com.space.event.core.Observer;
import com.space.event.events.HealthEvent;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.HealthComponent;
import com.space.game.components.PositionComponent;
import com.space.game.components.VelocityComponent;
import com.space.game.core.Entity;

public class GameUi implements Observer {

    Skin skin;
    Stage stage;
    Label fpsLabel,accelerationLabel,velocityLabel,positionLabel, collisionLabel, scoreLabel;
    Label healthLabel;
    Table debugTable;
    Table healthTable;
    Table persistentTable;
    int height, width;
    Float timer = 0.0f;
    String position = "";
    String velocity = "";
    String acceleration = "";
    String collision = "";
    String score = "0";
    String health = "0";

    public GameUi(int width, int height) {
        this.healthTable = new Table();
        this.persistentTable = new Table();
        this.healthTable.setPosition(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        this.stage = new Stage();
        this.skin = new Skin();
        this.width = width;
        this.height = height;
        createUi();
    }

    public void setViewPort(Viewport viewport){
        this.stage.setViewport(viewport);
    }

    private void createUi(){
        this.skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
        scoreLabel = new Label("0", this.skin);
        persistentTable.setPosition(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT-30);
        persistentTable.add(scoreLabel);
        this.healthLabel = new Label("0",this.skin);
        this.healthTable.add(healthLabel);
        Gdx.input.setInputProcessor(stage);
        if(Constants.DEBUGUITEXT) {
            createDebugUi();
            stage.addActor(debugTable);

        }
        stage.addActor(persistentTable);
        stage.addActor(healthTable);
        stage.setDebugAll(Constants.DEBUGUI);
    }
    public void createDebugUi(){
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        fpsLabel = new Label("fps:", this.skin);
        accelerationLabel = new Label("acceleration:", this.skin);
        velocityLabel = new Label("velocity:", this.skin);
        positionLabel = new Label("position:", this.skin);
        collisionLabel = new Label("", this.skin);
        collisionLabel.setPosition(100,100);

        debugTable = new Table();
        debugTable.add(fpsLabel).width(100).row();
        debugTable.add(accelerationLabel).width(100).row();
        debugTable.add(velocityLabel).width(100).row();
        debugTable.add(positionLabel).width(100).row();
        debugTable.add(collisionLabel).width(100).row();
        debugTable.setPosition(this.width/2.0f, height-100);
        debugTable.setBackground(textureRegionDrawableBg);
    }

    public void update(float dt){

        drawHealthLabel();
        updateScore(dt);
        timer+=dt;
        if (timer >= 0.05) {
            timer -= 0.05f;
            updateDebug(dt);
        }
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 10f));
        this.stage.draw();

    }

    private void drawHealthLabel(){
        healthLabel.setText(this.health);
    }

    public void updateDebug(float dt){
        if(Constants.DEBUGUITEXT) {
            fpsLabel.setText("fps: " + Gdx.graphics.getFramesPerSecond());
            positionLabel.setText(this.position);
            velocityLabel.setText(this.velocity);
            accelerationLabel.setText(this.acceleration);
            collisionLabel.setText(this.collision);
        }
    }

    public void updateScore(float dt){

        scoreLabel.setText("Score \n"+this.score);
        scoreLabel.setAlignment(Align.center);

    }

    public Stage getStage(){
        return this.stage;
    }

    @Override
    public void onNotify(Entity entity, Event event) {
        switch (event.getEventType())
        {
            case DEBUG_ACCELERATION:
                acceleration = event.message;
                break;
            case DEBUG_VELOCITY:
                velocity = event.message;
                break;
            case DEBUG_POSITION:
                position = event.message;
                break;
            case DEBUG_COLLISION:
                collision = event.message;
                break;
            case SCORING_EVENT:
                this.score = event.message;
                break;
            case HEALTH_EVENT:
                this.health = event.message;
                int textwidth = 1;
                healthTable.setPosition(entity.getComponent(PositionComponent.class).getPosition().x-textwidth,
                        entity.getComponent(PositionComponent.class).getPosition().y);
                break;
        }
    }
}
