package com.space.event.events;

import com.space.event.core.Event;
import com.space.event.core.EventTypes;

public class ScoringEvent extends Event {

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public ScoringEvent(){
        eventType = EventTypes.SCORING_EVENT;
    }

    public ScoringEvent(EventTypes eventTypes){
        eventType = eventTypes;
    }

    @Override
    public EventTypes getEventType() {
        return eventType;
    }
}
