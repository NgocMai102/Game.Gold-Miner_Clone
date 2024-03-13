package engine.windows.node.Object.Underground;

import engine.windows.common.Position;
import engine.windows.node.Object.Taker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rock extends UndergroundObject{

    String type;

    public Rock(String type, Position position, Taker taker) {
        super(position, taker);
        this.type = type;
        this.initRock();
    }
    public void initRock() {
        try {
            if (type == "big") {
                mass = 100;
                image = ImageIO.read(new File("Resources/Rock/bigRock.png"));
                value = 20;
            } else if (type == "small") {
                mass = 80;
                image = ImageIO.read(new File("Resources/Rock/smallRock.png"));
                value = 11;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getType() {
        return type;
    }
}
