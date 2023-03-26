package com.space.game.systems;

import com.space.event.core.Observer;
import com.space.game.entities.Entity;

import java.util.List;


public abstract class GameSystem {

    public abstract void update(List<Entity> entities, float dt);

    public abstract void addObserver(Observer ob);
}
