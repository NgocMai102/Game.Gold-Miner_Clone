package engine.windows.node.Object.Underground;

import engine.windows.common.Position;
import engine.windows.node.GameObject;
import engine.windows.node.Object.Taker;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UndergroundObject extends GameObject {

    int mass;
    int value;
    Taker taker;
    Position objectCenter;

    public UndergroundObject(Position position,Taker taker) {
        super(position);
        this.taker = taker;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void collideWith(GameObject target) {
        if(target instanceof Taker)
            isCollided();
    }

    public int getValue() {
        return value;
    }

    public float getMass() {
        return mass;
    }
}
