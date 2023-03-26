package com.space.event.core;

import com.space.game.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    List<Observer> observerList = new ArrayList<>();
    int numObservers = 0;

    public void addObserver(Observer ob) {
        observerList.add(ob);
    }

    //incorrect fix later
    public void removeObserver(Observer ob) {
        observerList.remove(ob);
    }

    public void notify(Entity entity, Event event) {
        for (Observer ob : observerList) {
            ob.onNotify(entity, event);
        }
    }
}
