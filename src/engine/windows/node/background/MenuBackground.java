package engine.windows.node.background;

import engine.windows.Tool;
import engine.windows.common.Animation;
import engine.windows.common.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class MenuBackground {
    BufferedImage Background;
    BufferedImage RockTile;
    BufferedImage Door;
    BufferedImage Floor;
    BufferedImage rightRock;
    BufferedImage leftRock;
    BufferedImage Title;

    BufferedImage body;
    BufferedImage head;
    BufferedImage light;

    int floorHeight = 540;

    int rocktileHeight = 300;

    double angle = 0 ;
    double ratio  = 0;
    double time = 0;
    double zoomScale = 1;
    BufferedImage rock1,rock2,rock3;
    BufferedImage star;
    Position startPos;
    public MenuBackground(){
        try {
            Background = ImageIO.read(new File("Resources/Menu/bg.png"));
            RockTile = ImageIO.read(new File("Resources/Menu/rocktile.png"));
            Door = ImageIO.read(new File("Resources/Menu/door.png"));
            Floor = ImageIO.read(new File("Resources/Menu/floor.png"));
            rightRock = ImageIO.read(new File("Resources/Menu/RightRock.png"));
            leftRock = ImageIO.read(new File("Resources/Menu/LeftRock.png"));
            Title = ImageIO.read(new File("Resources/Menu/Titile.png"));
            body = ImageIO.read(new File("Resources/Menu/body.png"));
            head = ImageIO.read(new File("Resources/Menu/head.png"));
            light = ImageIO.read(new File("Resources/Menu/sunray-sheet0.png"));
            star = ImageIO.read(new File("Resources/Menu/shinetitle-sheet0.png"));
            rock1 = ImageIO.read(new File("Resources/Menu/Rock1.png"));  rock2 = ImageIO.read(new File("Resources/Menu/Rock2.png"));  rock3 = ImageIO.read(new File("Resources/Menu/Rock3.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startPos = new Position(1020 - body.getWidth() + 35,525);
    }

    public void draw(Graphics g){
        for(int i = 0;i<3;i++){
            g.drawImage(Background,i*Background.getWidth(),0,null);
            g.drawImage(Background,i*Background.getWidth(),Background.getHeight(),null);
        }
        g.drawImage(RockTile,0,rocktileHeight,null);   g.drawImage(RockTile,RockTile.getWidth(),rocktileHeight,null);
        g.drawImage(Door,1020 - Door.getWidth() - 90,200,null);
        g.drawImage(Floor,0,floorHeight,null); g.drawImage(Floor,Floor.getWidth(),floorHeight,null);
        g.drawImage(rightRock,1440 - rightRock.getWidth(),0,null);
        g.drawImage(leftRock,0,0,null);
        g.drawImage(rock1,960,565,null);
        g.drawImage(rock2,930,600,null);
        g.drawImage(rock3,1200,550,null);   g.drawImage(rock3,500,630,null);
        g.drawImage(Tool.ScaleImage(Tool.rotate(light,ratio),2.5),1020 - body.getWidth() + 28 - Tool.ScaleImage(light,2.5).getWidth()/2 ,520 - Tool.ScaleImage(light,2.5).getHeight()/2,null);
        g.drawImage(body, 1020 - body.getWidth() - 105,405,null);
        g.drawImage(Tool.rotateCenter(head,angle),1020 - head.getWidth() - 90,350,null);
        g.drawImage(Title, 370,100,null);
        g.drawImage(Tool.ScaleImage(Tool.rotate(star,ratio/1.5),zoomScale-0.3),450 - Tool.ScaleImage(Tool.rotate(star,ratio/1.5),zoomScale-0.3).getWidth()/2, 120 - Tool.ScaleImage(Tool.rotate(star,ratio/1.5),zoomScale-0.3).getHeight()/2,null);
        g.drawImage(Tool.ScaleImage(Tool.rotate(star,ratio*1.2),zoomScale-0.5),700 - Tool.ScaleImage(Tool.rotate(star,ratio*1.2),zoomScale-0.5).getWidth()/2, 180 - Tool.ScaleImage(Tool.rotate(star,ratio*1.2),zoomScale-0.5).getHeight()/2,null);
        g.drawImage(Tool.ScaleImage(Tool.rotate(star,ratio/2),zoomScale-0.7),850 - Tool.ScaleImage(Tool.rotate(star,ratio/2),zoomScale-0.7).getWidth()/2, 120 - Tool.ScaleImage(Tool.rotate(star,ratio/2),zoomScale-0.7).getHeight()/2,null);
        g.drawImage(Tool.ScaleImage(Tool.rotate(star,ratio/1.3),zoomScale-0.6),1050 - Tool.ScaleImage(Tool.rotate(star,ratio/1.3),zoomScale-0.6).getWidth()/2, 180 - Tool.ScaleImage(Tool.rotate(star,ratio/1.3),zoomScale-0.6).getHeight()/2,null);
    };

    public void update(){
        time += 0.1;
        angle = Math.PI/48*Math.cos(3.0/2/Math.PI*time);
        ratio = 0.03*Math.PI*time;
        zoomScale = 1 + 0.1*Math.cos(Math.PI*time);
    };
    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w+20, h+20, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(angle, w/2, h/2);
        graphic.drawImage(bimg, null, 5, 5);
        graphic.dispose();
        return rotated;
    }

    public Position getStartPos() {
        return startPos;
    }
}
