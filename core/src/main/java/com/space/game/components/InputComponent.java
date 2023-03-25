package com.space.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;


import java.util.HashMap;
import java.util.Map;


import com.space.game.core.Component;

public class InputComponent extends Component {

    protected Map<Integer, Vector2> inputMap = new HashMap<>();
    public Vector2 direction = new Vector2(0f,0f);

    @Override
    public void update(float dt) {
        Vector2 resultDirection = new Vector2(0.0f,0.0f);
        for(Map.Entry<Integer, Vector2> input : inputMap.entrySet()){
            int key = input.getKey();
            Vector2 value = input.getValue();
            if(Gdx.input.isKeyPressed(key)){
                resultDirection.x += value.x;
                resultDirection.y += value.y;
            }
        }
        resultDirection.clamp(-1.0f,1.0f);
        direction.x = resultDirection.x;
        direction.y = resultDirection.y;
    }

    public void start(){

    }

    public void addInput(int key, Vector2 value){
        inputMap.put(key,value);
    }

    public float getDirectionX() {
        return this.direction.x;
    }

    public float getDirectionY() {
        return this.direction.y;
    }
    public Vector2 getDirection() {
        return this.direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
