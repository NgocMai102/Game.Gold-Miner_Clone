package engine.windows.node.background;

import engine.windows.GameWindows;
import engine.windows.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameBackground {

    private final BufferedImage bomb;
    private final BufferedImage bgtile1,bgtile2,bgtile3,bgtile4;

    private final BufferedImage purpletile;

    private final BufferedImage groundtile;

    private final BufferedImage gametopbg;
    Integer type;
    Integer maxLevel = 3;


    BufferedImage dirt;

    BufferedImage money,time,target,level;


    public GameBackground(){
        try {
            bgtile1 = ImageIO.read(new File("Resources/GameBackGround/bgtile1.png"));   bgtile2 = ImageIO.read(new File("Resources/GameBackGround/bgtile2.png"));   bgtile3  = ImageIO.read(new File("Resources/GameBackGround/bgtile3.png"));  bgtile4  = ImageIO.read(new File("Resources/GameBackGround/bgtile4.png"));
            purpletile = ImageIO.read(new File("Resources/GameBackGround/purpletile.png"));
            groundtile = ImageIO.read(new File("Resources/GameBackGround/groundtile.png"));
            gametopbg = ImageIO.read(new File("Resources/GameBackGround/gametopbg.png"));
            dirt = ImageIO.read(new File("Resources/GameBackGround/dirt.png"));
            money = ImageIO.read(new File("Resources/GameSceneObject/Money.png"));
            level = ImageIO.read(new File("Resources/GameSceneObject/Level.png"));
            target = ImageIO.read(new File("Resources/GameSceneObject/Target.png"));
            time = ImageIO.read(new File("Resources/GameSceneObject/Time.png"));
            bomb = ImageIO.read(new File("Resources/GameSceneObject/bomb.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics g){
        g.drawImage(dirt,0,100,null);
        for(int i = 0;i<=1440;i+=purpletile.getWidth()){
            g.drawImage(purpletile,i,-30,null);
            g.drawImage(purpletile,i,purpletile.getHeight()-30,null);
        }
        for(int i = 0;i<=1440;i+=gametopbg.getWidth()) {
            g.drawImage(gametopbg,i,2*purpletile.getHeight() - gametopbg.getHeight()-30,null);
        }
        for(int i = 0;i<=1440;i+=groundtile.getWidth()) {
            g.drawImage(groundtile, i, 2 * purpletile.getHeight() - groundtile.getHeight(), null);
        }
        for(int i = 0;i<=1440;i+=bgtile1.getWidth()){
            g.drawImage(bgtile1, i, 2 * purpletile.getHeight() + bgtile1.getHeight() - 30, null);
        }
        for(int i = 0;i<=1440;i+=bgtile2.getWidth()){
            g.drawImage(bgtile2, i, 2 * purpletile.getHeight() + bgtile1.getHeight() + bgtile2.getHeight() -15 , null);
        }
        for(int i = 0;i<=1440;i+=bgtile3.getWidth()){
            g.drawImage(bgtile3, i, 2 * purpletile.getHeight() + bgtile1.getHeight() + bgtile2.getHeight() + bgtile3.getHeight() +8, null);
        }
        for(int i = 0;i<=1440;i+=bgtile4.getWidth()){
            g.drawImage(bgtile4, i, 2 * purpletile.getHeight() + bgtile1.getHeight() + bgtile2.getHeight() + bgtile3.getHeight() + bgtile4.getHeight()/2, null);
        }
        g.drawImage(level,10,60,null);
        g.drawImage(target,10 ,  110,null);
        g.drawImage(time, 1440 - 200,60 ,null);
        g.drawImage(money,1440 - 205, 110,null);
        g.drawImage(bomb,820,70,null);
    }
}
