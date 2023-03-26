package com.space.game.entities.internal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Shape2D;
import com.space.game.components.CollisionComponent;
import com.space.game.components.GraphicsCompoment;
import com.space.game.components.external.Component;
import com.space.game.entities.BackgroundEntity;
import com.space.game.entities.EntityBuilder;

import java.util.ArrayList;
import java.util.List;

public class BackgroundBuilder implements EntityBuilder<BackgroundEntity> {


    private String name;
    private List<Component> componentList;


    public BackgroundBuilder(String name) {
        this.name = name;
        this.componentList = new ArrayList<>();

    }


    @Override
    public EntityBuilder graphicsComponent(Texture texture, int sizeX, int sizeY) {
        addComponent(new GraphicsCompoment(texture, sizeX, sizeY));
        return this;
    }

    @Override
    public EntityBuilder healthComponent() {
        return null;
    }

    @Override
    public EntityBuilder inputComponent() {
        return null;
    }

    @Override
    public EntityBuilder particleComponent() {
        return null;
    }

    @Override
    public EntityBuilder positionComponent() {
        return null;
    }

    @Override
    public EntityBuilder powerComponent() {
        return null;
    }

    @Override
    public EntityBuilder scoreComponent() {
        return null;
    }

    @Override
    public EntityBuilder velocityComponent() {
        return null;
    }

    @Override
    public BackgroundEntity build() {
        return new BackgroundEntity(this);
    }

    public <S extends Shape2D> BackgroundBuilder collisionComponent(S shape2D) {
        addComponent(new CollisionComponent<>(shape2D));
        return this;
    }

    private void addComponent(Component newComponent) {
        for (Component component : componentList) {
            if (component.getClass() != newComponent.getClass()) {
                componentList.add(newComponent);
            } else {
                assert false : "Trying to add same component twice";
            }
        }
    }
}
