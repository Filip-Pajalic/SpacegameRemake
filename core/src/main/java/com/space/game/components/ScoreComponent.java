package com.space.game.components;

import com.space.game.components.external.Component;

public class ScoreComponent implements Component {

    int score = 0;

    @Override
    public void update(float dt) {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore(int value) {
        this.score += value;
    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }
}
