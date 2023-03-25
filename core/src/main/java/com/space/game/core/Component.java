package com.space.game.core;

public abstract class Component {

    public Entity entity = null;

    public void start(){

    }

    public abstract void update(float dt);

}
