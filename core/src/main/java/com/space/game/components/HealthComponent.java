package com.space.game.components;


import com.space.game.core.Component;


public class HealthComponent extends Component {

    public int health = 0;

    @Override
    public void update(float dt) {

    }

    public void setHealth(int health){

        this.health = health;

    }

    public void removeHealth(int health){
        if (this.health - health <=0){
            this.health = 0;
        }
        else
            this.health -= health;
    }

    public int getHealth() {
        return health;
    }
}
