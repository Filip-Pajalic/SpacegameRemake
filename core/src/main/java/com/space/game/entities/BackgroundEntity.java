package com.space.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.PositionComponent;
import com.space.game.core.Entity;


public class BackgroundEntity extends Entity {


    public BackgroundEntity(String name, int sizeX, int sizeY) {
        super(name);
        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("assets/background.png"));
        getComponent(GraphicsCompoment.class).setSizeX(sizeX);
        getComponent(GraphicsCompoment.class).setSizeY(sizeY);
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(new Vector2(0,0));
    }
}
