package com.space.event.observers;
import com.space.event.core.Event;
import com.space.event.core.Observer;
import com.space.game.core.Entity;

public class DebugObserver implements Observer {

    @Override
    public void onNotify(Entity entity, Event event) {
        switch (event.getEventType())
        {
            case DEBUG_TEXT:
                debugMessage(event);
                break;
            case DEBUG_VELOCITY:
                debugMessage(event);
                break;
            case DEBUG_ACCELERATION:
                debugMessage(event);
            case DEBUG_POSITION:
                debugMessage(event);
        }
    }
    private void debugMessage(Event event)
    {
        System.out.printf("event: %s", event.getMessage());
    }
}
