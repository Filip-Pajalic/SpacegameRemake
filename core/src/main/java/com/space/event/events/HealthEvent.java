package com.space.event.events;

import com.badlogic.gdx.math.Vector2;

import com.space.event.core.Event;
import com.space.event.core.EventTypes;

public class HealthEvent extends Event {

    private Vector2 position;

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HealthEvent(){
        eventType = EventTypes.HEALTH_EVENT;
    }

    public HealthEvent(EventTypes eventTypes){
        eventType = eventTypes;
    }

    @Override
    public EventTypes getEventType() {
        return eventType;
    }

    public Vector2 getPosition(){
        return position;
    }

    public void setPosition(Vector2 position){
        this.position = position;
    }
}
