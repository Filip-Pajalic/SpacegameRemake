package com.space.event.core;

public abstract class Event{

    public EventTypes eventType= null;
    public String message = null;

    public abstract void setMessage(String message);

    public abstract String getMessage();

    public abstract EventTypes getEventType();
}

