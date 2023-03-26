package com.space.event.core;

import com.space.game.entities.Entity;

public interface Observer {

    void onNotify(Entity entity, Event event);
}
