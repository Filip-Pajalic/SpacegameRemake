package com.space.game.entities;


import com.badlogic.gdx.math.Shape2D;
import com.space.game.components.CollisionComponent;
import com.space.game.components.external.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    protected String name;
    protected List<Component> componentList;

    protected Entity(EntityBuilder entityBuilder) {
        name = entityBuilder.name;
        componentList = entityBuilder.componentList;
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c : componentList) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    assert false : "Error casting component";
                }
            }
        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) { //concurrentmodificationerror?
        for (Component c : componentList) {
            if (componentClass.isAssignableFrom(c.getClass())) {
                componentList.remove(c);
                return;
            }
        }
    }

    public void addComponent(Component newComponent) {
        for (Component component : componentList) {
            if (component.getClass() != newComponent.getClass()) {
                componentList.add(newComponent);
            } else {
                assert false : "Trying to add same component twice";
            }
        }
    }

    public void update(float dt) {
        for (Component component : componentList) {
            component.update(dt);
        }
    }

    public void start() {
        for (Component component : componentList) {
            component.start();
        }
    }

    public static class EntityBuilder<T extends Entity> {

        private String name;
        private List<Component> componentList;


        public EntityBuilder(String name) {
            this.name = name;
            this.componentList = new ArrayList<>();

        }

        public <S extends Shape2D> EntityBuilder collisionComponent(S shape2D) {
            addComponent(new CollisionComponent<>(shape2D));
            return this;
        }


        public T build() {
            return new T(this);

        }


    }
}
