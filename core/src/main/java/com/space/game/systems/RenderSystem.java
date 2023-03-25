package com.space.game.systems;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.space.Constants;
import com.space.event.core.Observer;
import com.space.game.components.*;
import com.space.game.core.Entity;
import com.space.game.core.GameSystem;
import com.space.game.entities.BackgroundEntity;
import com.space.gui.GameUi;

import java.util.List;


public class RenderSystem extends GameSystem {
    private final SpriteBatch batch;
    private GameUi gameUi;

    private final ShapeRenderer shapeRenderer;
    private Camera camera;
    GraphicsCompoment backgroundGraphics;
    PositionComponent backgroundPosition;


    private Viewport viewport;

    public RenderSystem(int width, int height) {
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(width, height, this.camera);
        BackgroundEntity background = new BackgroundEntity("background", width, height);
        this.backgroundGraphics = background.getComponent(GraphicsCompoment.class);
        this.backgroundPosition = background.getComponent(PositionComponent.class);
    }

    public void setGameUi(GameUi gameUi) {
        this.gameUi = gameUi;
        this.gameUi.getStage().setViewport(this.viewport);
    }

    @Override
    public void update(List<Entity> entityList, float dt) {
        this.batch.begin();
        this.batch.draw(backgroundGraphics.getTexture(), backgroundPosition.getPosition().x, backgroundPosition.getPosition().y, backgroundGraphics.getSizeX(), backgroundGraphics.getSizeY());
        this.batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        renderShape(entityList);
        renderEntity(entityList);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        // gameUi.update(dt);
    }

    public void renderEntity(List<Entity> entityList) {
        for (Entity entity : entityList) {
            GraphicsCompoment entityGraphics = entity.getComponent(GraphicsCompoment.class);
            PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
            PowerComponent entityPower = entity.getComponent(PowerComponent.class);
            ParticleComponent entityParticle = entity.getComponent(ParticleComponent.class);
            this.batch.begin();
            renderTextures(entityGraphics, entityPosition, entityPower);
            //renderParticles(entityPosition, entityPower, entityParticle);
            this.batch.end();
        }

    }

    public void renderTextures(GraphicsCompoment entityGraphics, PositionComponent entityPosition, PowerComponent entityPower) {
        if (entityGraphics != null && entityPosition != null) {
            if (entityGraphics.getTexture() != null) {
                this.batch.draw(entityGraphics.getTexture(), entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX(), entityGraphics.getSizeY());
                //replace this with a loop that gets the name of the power and checks if there is a texture with the name.
                if (entityPower.isPower("MainThruster") && entityPower.getTexture("MainThruster") != null) {
                    this.batch.draw(entityPower.getTexture("MainThruster"), entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX(), entityGraphics.getSizeY());
                }
            }
        }
    }

    public void renderParticles(PositionComponent entityPosition, PowerComponent entityPower, ParticleComponent entityParticle) {

        if (entityPower != null) {
            if (entityPower.isPower("MainThruster") && entityParticle.getParticleEffectList().get(0) != null) {
                entityParticle.getParticleEffectList().get(0).setPosition(entityPosition.getPosition().x + 32, entityPosition.getPosition().y + 20);
                entityParticle.getParticleEffectList().get(0).draw(this.batch, Gdx.graphics.getDeltaTime());
            }
            if (entityPower.isPower("Explosion") && entityParticle != null) {
                for (ParticleEffect pe : entityParticle.getParticleEffectList()) {
                    pe.draw(this.batch, Gdx.graphics.getDeltaTime());
                }
            }
        }
    }

    public void renderShape(List<Entity> entityList) {
        this.shapeRenderer.setAutoShapeType(true);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        //draw hitboxes
        entityList.forEach(entity -> {
            GraphicsCompoment entityGraphics = entity.getComponent(GraphicsCompoment.class);
            PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
            CollisionComponent entityCollision = entity.getComponent(CollisionComponent.class);
            if (entityGraphics != null && entityPosition != null && entityGraphics.getShape() != null) {
                switch (entityGraphics.getShape()) {
                    case CIRCLE -> {
                        this.shapeRenderer.setColor(entityGraphics.getColor());
                        this.shapeRenderer.circle(entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX());
                        if (entityGraphics.getFilledColor() != null) {
                            this.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
                            this.shapeRenderer.setColor(entityGraphics.getFilledColor());
                            this.shapeRenderer.circle(entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX());
                            this.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
                        }
                    }
                    case RECANGLE ->
                        this.shapeRenderer.rect(entityPosition.getPosition().x, entityPosition.getPosition().y, entityGraphics.getSizeX(), entityGraphics.getSizeY());
                }
            }
            if (Constants.DEBUG && entityCollision != null && entityPosition != null) {
                this.shapeRenderer.setColor(Color.RED);
                if (entityCollision.getCircle() == null && entityCollision.getRect() != null) {
                    this.shapeRenderer.rect(entityCollision.getPosition().x, entityCollision.getPosition().y, entityCollision.getRect().width, entityCollision.getRect().height);
                } else if (entityCollision.getRect() == null && entityCollision.getCircle() != null) {
                    this.shapeRenderer.circle(entityCollision.getPosition().x, entityCollision.getPosition().y, entityCollision.getCircle().radius);
                }


            }
        });
        this.shapeRenderer.end();

    }

    @Override
    public void addObserver(Observer ob) {

    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return this.shapeRenderer;
    }

    public Stage getStage() {
        return this.gameUi.getStage();
    }

    public void dispose() {
        this.batch.dispose();
        this.shapeRenderer.dispose();
        this.gameUi.getStage().dispose();
    }
}
