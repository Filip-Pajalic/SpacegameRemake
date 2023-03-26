package com.space.game.entities;

import com.badlogic.gdx.graphics.Texture;

public interface EntityBuilder<T extends Entity> {

    EntityBuilder graphicsComponent(Texture texture, int sizeX, int sizeY);

    EntityBuilder healthComponent();

    EntityBuilder inputComponent();

    EntityBuilder particleComponent();

    EntityBuilder positionComponent();

    EntityBuilder powerComponent();

    EntityBuilder scoreComponent();

    EntityBuilder velocityComponent();

    T build();

}
