package com.space.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.PositionComponent;
import com.space.game.entities.internal.BackgroundBuilder;


public class BackgroundEntity extends Entity {


    protected BackgroundEntity(EntityBuilder builder) {
        super(builder);

        addComponent(new GraphicsCompoment());
        getComponent(GraphicsCompoment.class).setTexture(new Texture("assets/background.png"));
        getComponent(GraphicsCompoment.class).setSizeX(sizeX);
        getComponent(GraphicsCompoment.class).setSizeY(sizeY);
        addComponent(new PositionComponent());
        getComponent(PositionComponent.class).setPosition(new Vector2(0, 0));
    }

    public static BackgroundEntity createClass() {
        return new BackgroundBuilder("background").graphicsComponent(new Texture("assets/background.png"), 10, 10).positionComponent().build();
    }

    public void changeBackgroundTexture(Texture texture) {
        final GraphicsCompoment component = getComponent(GraphicsCompoment.class);
        if (component != null) {
            component.setTexture(texture);
        }

    }
}
