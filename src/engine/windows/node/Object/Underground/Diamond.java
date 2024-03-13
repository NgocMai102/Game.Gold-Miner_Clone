package engine.windows.node.Object.Underground;

import engine.windows.common.Animation;
import engine.windows.common.Position;
import engine.windows.node.Object.Taker;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diamond extends UndergroundObject{
    public Diamond(Position position, Taker taker) {

        super(position, taker);
        mass = 10;
        this.value = 500;
        try {
            image = ImageIO.read(new File("Resources/Diamond/diamond.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
