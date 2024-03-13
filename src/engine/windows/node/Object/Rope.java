package engine.windows.node.Object;

import engine.windows.Tool;
import engine.windows.common.Position;
import engine.windows.node.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rope extends GameObject {
    Taker taker;

    Position connectPosition;
    float originalLength;
    public Rope(Position position,Taker taker) {
        super(position);
        this.taker = taker;
        collidable = false;
        try {
            image = ImageIO.read(new File("Resources/GameSceneObject/ropetile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //originalLength = position.distantTo(new Position((int)taker.getPosition().x+taker.getImage().getWidth()/2,(int)taker.getPosition().y + taker.getImage().getHeight() / 2));
        //originalLength = 50;
    }


    @Override
    public void update() {
        super.update();
    }

    public void draw(Graphics g){
        g.drawLine((int)position.x+taker.getImage().getWidth()/2,(int)position.y,(int)taker.getPosition().x+taker.getImage().getWidth()/2,(int)taker.getPosition().y + taker.getImage().getHeight() / 2);
//        float currentLength = position.distantTo(new Position((int) taker.getPosition().x + taker.getImage().getWidth() / 2, (int) taker.getPosition().y + taker.getImage().getHeight() / 2));
//        if(currentLength + 20 < originalLength) {
//            taker.reset();
//        }
    }

    @Override
    public void collideWith(GameObject target) {
    }
}
