package com.space.game.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Shape2D;
import com.space.game.components.external.Component;


public class GraphicsCompoment implements Component {


    private Texture texture = null;
    private Shape2D shape = null;
    private Color color = null;

    private int sizeX = 0; //radius
    private int sizeY = 0;

    private boolean filled = false;
    private Color filledColor = null;

    public GraphicsCompoment(Texture texture, int sizeX, int sizeY) {
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean isEnabled(final boolean enable) {
        return true;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public void setShape(Shape2D shape) {
        this.shape = shape;
    }

    public Shape2D getShape() {
        return shape;

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFilled(Color color) {
        filled = true;
        filledColor = color;
    }

    public Color getFilledColor() {
        return this.filledColor;
    }

}
