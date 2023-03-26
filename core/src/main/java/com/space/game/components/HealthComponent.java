package com.space.game.components;


import com.space.game.components.external.Component;

public class HealthComponent implements Component {

    public int health = 0;

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }

    public void setHealth(int health) {

        this.health = health;

    }

    public void removeHealth(int health) {
        if (this.health - health <= 0) {
            this.health = 0;
        } else
            this.health -= health;
    }

    public int getHealth() {
        return health;
    }
}
