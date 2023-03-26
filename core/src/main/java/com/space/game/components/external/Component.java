package com.space.game.components.external;

public interface Component {

    default void start() {

    }

    void update(float dt);

    boolean isEnabled(boolean enable);

}
