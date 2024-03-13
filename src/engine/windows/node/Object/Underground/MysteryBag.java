package engine.windows.node.Object.Underground;

import engine.windows.common.Animation;
import engine.windows.common.Position;
import engine.windows.node.Object.Taker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MysteryBag extends UndergroundObject{
    public MysteryBag(Position position, Taker taker) {
        super(position, taker);
        try {
            image = ImageIO.read(new File("Resources/GameSceneObject/MysteryBag.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
