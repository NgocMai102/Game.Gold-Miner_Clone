package engine.windows.node.Object;

import engine.windows.common.Position;
import engine.windows.node.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Human {
    BufferedImage body;
    BufferedImage head;
    BufferedImage arm;
    BufferedImage rope;
    public Human(Taker taker){
        try {
            this.head = ImageIO.read(new File("Resources/Man/head1.png"));
            this.arm = ImageIO.read(new File("Resources/Man/hand.png"));
            this.body = ImageIO.read(new File("Resources/Man/body.png"));
            this.rope = ImageIO.read(new File("Resources/Man/roll.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics g){
        g.drawImage(rope,720 ,160,null);
        g.drawImage(arm,770,150,null);
        g.drawImage(body,720 + body.getWidth(),150,null);
        g.drawImage(head,710 + body.getWidth(),85,null);


    }
}
