package com.space.event.core;

import java.util.List;

import com.space.game.core.Entity;

public interface Observer {

    void onNotify(Entity entity, Event event);
}
