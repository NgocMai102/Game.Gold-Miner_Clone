package engine.windows;

import engine.windows.common.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Score {
    BufferedImage n0,n1,n2,n3,n4,n5,n6,n7,n8,n9;
    int N;
    String revN;

    Position position;

    int x;
    public Score(int N,Position position){
        this.N = N;
        this.position = position;
        try {
            n1 = ImageIO.read(new File("Resources/Number/1.png"));
            n0 = ImageIO.read(new File("Resources/Number/0.png"));
            n2 = ImageIO.read(new File("Resources/Number/2.png"));
            n3 = ImageIO.read(new File("Resources/Number/3.png"));
            n4 = ImageIO.read(new File("Resources/Number/4.png"));
            n5 = ImageIO.read(new File("Resources/Number/5.png"));
            n6 = ImageIO.read(new File("Resources/Number/6.png"));
            n7 = ImageIO.read(new File("Resources/Number/7.png"));
            n8 = ImageIO.read(new File("Resources/Number/8.png"));
            n9 = ImageIO.read(new File("Resources/Number/9.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics g){
        int tempN = N;
        revN = "";
        while(tempN>0){
            revN += tempN%10;
            tempN/=10;
        }
        if(N == 0) {
            revN = "0";
        }
        int x = 0;
        for(int i = revN.length()-1;i>=0;i--){
            if(revN.charAt(i)=='0') {
                g.drawImage(n0,(int) position.x+x,(int) position.y,null);
                x+=n0.getWidth();
            }
            if(revN.charAt(i)=='1') {
                g.drawImage(n1,(int) position.x+x,(int) position.y,null);
                x+=n1.getWidth()+2;
            }
            if(revN.charAt(i)=='2') {
                g.drawImage(n2,(int) position.x+x,(int) position.y,null);
                x+=n2.getWidth()+2;
            }
            if(revN.charAt(i)=='3') {
                g.drawImage(n3,(int) position.x+x,(int) position.y,null);
                x+=n3.getWidth()+2;
            }
            if(revN.charAt(i)=='4') {
                g.drawImage(n4,(int) position.x+x,(int) position.y,null);
                x+=n4.getWidth()+2;
            }
            if(revN.charAt(i)=='5') {
                g.drawImage(n5,(int) position.x+x,(int) position.y,null);
                x+=n5.getWidth()+2;
            }
            if(revN.charAt(i)=='6') {
                g.drawImage(n6,(int) position.x+x,(int) position.y,null);
                x+=n6.getWidth()+2;
            }
            if(revN.charAt(i)=='7') {
                g.drawImage(n7,(int) position.x+x,(int) position.y,null);
                x+=n7.getWidth()+2;
            }
            if(revN.charAt(i)=='8') {
                g.drawImage(n8,(int) position.x+x,(int) position.y,null);
                x+=n8.getWidth() + 2;
            }
            if(revN.charAt(i)=='9') {
                g.drawImage(n9,(int) position.x+x,(int) position.y,null);
                x+=n9.getWidth() + 2;
            }
        }
    }

    public void addNumber(int i) {
        N += i;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        this.N = n;
    }
}
