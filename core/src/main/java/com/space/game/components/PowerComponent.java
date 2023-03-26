package com.space.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.space.game.components.external.Component;

import java.util.HashMap;
import java.util.Map;

public class PowerComponent implements Component {

    private final Map<String, Boolean> powerList = new HashMap<>();

    private Map<String, Texture> textureList = new HashMap<>();

    private Map<String, Float> powerTimer = new HashMap<>();

    @Override
    public void update(float dt) {
        for (Map.Entry<String, Float> entry : powerTimer.entrySet()) {
            Float powerTimer = entry.getValue();
            if (entry.getValue() >= 0.0f) {
                powerTimer -= dt;
            }
            setPowerTimer(entry.getKey(), powerTimer);
        }
    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }

    public boolean isPower(String powerName) {
        if (this.powerList.containsKey(powerName)) {
            return this.powerList.get(powerName);
        }
        return false;
    }

    public void setPower(String powerName, boolean power) {
        this.powerList.put(powerName, power);
    }

    public Texture getTexture(String powerName) {
        return this.textureList.get(powerName);
    }

    public void setTexture(String powerName, Texture texture) {
        this.textureList.put(powerName, texture);
    }

    public boolean isPowerTimer(String powerName) {
        if (this.powerTimer.containsKey(powerName)) {
            return this.powerTimer.get(powerName) >= 0.0f;
        }
        return false;
    }

    public void setPowerTimer(String powerName, Float timer) {
        this.powerTimer.put(powerName, timer);
    }
}
