package engine.windows.node;

import engine.windows.common.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected Position position;
    protected BufferedImage image;

    protected boolean collidable = true;

    private boolean isTaked = false;

    public GameObject(Position position) {
        this.position = position;
    }

    public void update() {

    }

    abstract public void collideWith(GameObject target);

    public void draw(Graphics g) {
        g.drawImage(image,(int) position.x,(int) position.y, null);
    }

    public Position getPosition() {
        return position;
    }

    public void isCollided() {
        this.isTaked = true;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public boolean getStatus() {
        return isTaked;
    }

    public boolean isCollide(GameObject gameObject) {
        if(!this.collidable || !gameObject.collidable) return false;

        int x1 =(int) this.position.x;
        int y1 = (int) this.position.y;
        int width1 = this.image.getWidth();
        int height1 = this.image.getHeight();

        int x2 = (int)gameObject.position.x;
        int y2 = (int)gameObject.position.y;
        int width2 = gameObject.image.getWidth();
        int height2 = gameObject.image.getHeight();

        return !(x1 + width1 < x2 ||
                y1 + height1 < y2 ||
                x1 > x2 + width2 ||
                y1 > y2 + height2
        );

    }

    public BufferedImage getImage() {
        return image;
    }
}
