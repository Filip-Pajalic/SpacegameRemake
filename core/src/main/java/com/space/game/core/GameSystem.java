package com.space.game.core;

import com.space.event.core.Observer;

import java.util.List;



public abstract class GameSystem {

    public abstract void update(List<Entity> entities , float dt);

    public abstract void addObserver(Observer ob);
}
