package engine.windows.node.Object.Underground;

import engine.windows.common.Animation;
import engine.windows.common.Position;
import engine.windows.node.Object.Taker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gold extends UndergroundObject {
    Animation animation;

    List<BufferedImage> imageList;
    String type;
    public Gold(String type, Position position, Taker taker) {
        super(position, taker);
        this.type = type;
        this.initGold();
    }

    public void initGold() {
        try {
            if (type == "big") {
                mass = 80;
                image = ImageIO.read(new File("Resources/Gold/big.png"));
                value = 250;
            } else if (type == "medium") {
                mass = 50;
                image = ImageIO.read(new File("Resources/Gold/medium.png"));
                value = 100;
            } else if (type == "small") {
                mass = 25;
                image = ImageIO.read(new File("Resources/Gold/small.png"));
                value = 50;
            } else {
                mass = 100;
                image = ImageIO.read(new File("Resources/Gold/square.png"));
                value = 400;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getType() {
        return type;
    }
}


