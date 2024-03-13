package engine.windows.node.background;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PopUp {
    BufferedImage image;
    public PopUp(){
        try {
            image = ImageIO.read(new File("Resources/popup-sheet0.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g){
        g.drawImage(image,1440/2 - image.getWidth()/2,800/2-image.getHeight()/2,null);
    }
}
