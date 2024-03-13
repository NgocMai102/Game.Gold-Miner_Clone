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

public class Pig extends UndergroundObject {
    String type;
    List<BufferedImage> PigAnimate = new ArrayList<>();
    Animation PigMove;
    //2 loại type: Pig, dPig
    public Pig(String type, Position position, Taker taker) {
        super(position, taker);
        this.type = type;
        this.initPig();
    }

    public void initPig() {
        try {
            if (type == "Pig" || type == "pig") {
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/Pig1.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/Pig2.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/Pig3.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/Pig4.png")));
            } else {
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/dPig1.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/dPig2.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/dPig3.png")));
                PigAnimate.add(ImageIO.read(new File("Resources/Pig/dPig4.png")));
            }
            PigMove = new Animation(1000, PigAnimate, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        image = PigMove.getCurrentImage();
    }
    public void draw(Graphics g) {
        g.drawImage(image, (int) position.x, (int) position.y, null);
    }
}
