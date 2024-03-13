package engine.windows.node.Object;

import engine.windows.Tool;
import engine.windows.common.Position;
import engine.windows.node.GameObject;
import engine.windows.node.Object.Underground.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Taker extends GameObject {
    //------Oscillate------//
    private double w = 2/(2*Math.PI);
    private final int Radius = 49;
    private final int BIG_RADIUS = 50;
    private double time = 0;
    public double angle = 0;
    private double cst = 10;
    Position orgPos = new Position(700,150);
    //------ WTF ------//
    private int price;
    //------ Speed -------//
    private final static int THROWING_SPEED = 800;
    private final static int UPDATE_PER_SECOND = 60;
    float weightPercent = 1;
    //------- Status -------//
    boolean oscillate = true;
    boolean throwing = false;
    boolean pulling = false;
    boolean taked = false;
    boolean taken;

    //------ Set Status ------//

    public void setOscillate(boolean oscillate) {
        this.oscillate = oscillate;
    }

    public void setThrowing(boolean throwing) {
        this.throwing = throwing;
    }

    public void setPulling(boolean pulling) {
        this.pulling = pulling;
    }

    public void setTaked(boolean taked) {
        this.taked = taked;
    }
    //------ IDK ------//
    Position ThrowingPoint = new Position(0,0);
    BufferedImage subImage;
    //------ END -------//
    public Taker(Position position) {

        super(position);
        try {
            image = ImageIO.read(new File("Resources/GameSceneObject/Taker.png"));
            image = Tool.rotateByAnchor(image, 0.0, image.getWidth()/2, image.getHeight()/2 - 10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.subImage = null;
    }

    @Override
    public void update() {
        super.update();
        int xDirection = (position.x - orgPos.x <=0) ? 1 : - 1; /* Check Left-Right */
        if(isOscillate()) {
            angle = xDirection * Math.acos((double) (position.y - orgPos.y) / BIG_RADIUS);
            time += cst / UPDATE_PER_SECOND;
            this.position.x = orgPos.x + (int) (Radius * Math.cos(w * time));
            this.position.y = orgPos.y + (int) Math.pow(BIG_RADIUS * BIG_RADIUS - (position.x - orgPos.x) * (position.x - orgPos.x), 0.5);
        }
        if(isThrowing()){
            position.x += THROWING_SPEED*xDirection*(-1)*Math.abs(Math.sin(angle))/UPDATE_PER_SECOND;
            position.y += THROWING_SPEED*Math.abs(Math.cos(angle))/UPDATE_PER_SECOND;
        }
        /* Check OutWindow */
        if(position.x>=1440||position.x<=0||position.y>=800){
            pulling = true;
            throwing = false;
        }
        if(isPulling()){
            position.x -= THROWING_SPEED*xDirection*(-1)*Math.abs(Math.sin(angle))/UPDATE_PER_SECOND/weightPercent;
            position.y -= THROWING_SPEED*Math.abs(Math.cos(angle))/UPDATE_PER_SECOND/weightPercent;
        }
        /* Check Taker comeback */
        if(position.y <= 250 - image.getHeight()/2 && position.x >= 750 -image.getWidth() && position.x <= 750 && pulling == true) {
            pulling = false;
            reset();
            oscillate = true;
        }
        if(isTaked() == true){
            taked = false;
            oscillate =true;
            resetImage();
        }
    }

    private void resetImage() {
        subImage = null;
        try {
            image = ImageIO.read(new File("Resources/GameSceneObject/Taker.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image = Tool.rotateByAnchor(image, 0.0, image.getWidth()/2, image.getHeight()/2 - 10);
    }

    public void draw(Graphics g) {
        int xDirection = (position.x - orgPos.x <= 0) ? 1 : -1;
        BufferedImage rotated;
        rotated = Tool.rotateCenter(image, angle);
        if(subImage == null) {
            g.drawImage(rotated, (int)position.x , (int)position.y, null);
        } else {
            BufferedImage rotatedTemp = Tool.rotateCenter(subImage, angle);
            int dx = rotated.getWidth() - rotatedTemp.getWidth();
            int dy = rotated.getHeight() - rotatedTemp.getHeight();
            g.drawImage(rotatedTemp, (int)position.x + dx/2 , (int)position.y + dy/2, null);
        }
        //g.drawImage(image, position.x, position.y, null);
    }

    @Override
    public void collideWith(GameObject target) {
        if(target instanceof Gold){
            try {
                if(((Gold) target).getType() == "small"){
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/smallGold.png"));
                    subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
                }
                else if(((Gold) target).getType() == "medium") {
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/SGold.png"));
                    subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
                }
                else if(((Gold) target).getType() == "big") {
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/bigG.png"));
                    subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
                }
                else {
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/BGold.png"));
                    subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(target instanceof Rock){
            try {
                if(((Rock) target).getType()=="small")
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/Rock.png"));
                else
                    subImage = ImageIO.read(new File("Resources/GameSceneObject/BigRock.png"));
                subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(target instanceof Diamond){
            try {
                subImage = ImageIO.read(new File("Resources/GameSceneObject/Diamond.png"));
                subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(target instanceof Pig){
            try {
                subImage = ImageIO.read(new File("Resources/GameSceneObject/Diamond.png"));
                subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(target instanceof MysteryBag){
            try {
                subImage = ImageIO.read(new File("Resources/GameSceneObject/MysteryBag.png"));
                subImage = Tool.rotateByAnchor(subImage, 0.0, subImage.getWidth()/2, subImage.getHeight()/2 - 10);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(target instanceof Bone){}
        price = ((UndergroundObject) target).getValue();
        weightPercent = ((UndergroundObject) target).getMass()/10;
        pulling = true;
        throwing = false;
        taken = true;
    }

    public Position getOrgPos() {
        return orgPos;
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isOscillate() {
        return oscillate;
    }

    public boolean isThrowing() {
        return throwing;
    }

    public boolean isPulling() {
        return pulling;
    }

    public boolean isTaked() {
        return taked;
    }

    public boolean isTaken(){return taken;}

    public void setThrowingPoint(Position throwingPoint) {
        ThrowingPoint = throwingPoint;
    }


    public void reset() {
        pulling = false;
        throwing = false;
        oscillate = true;
        taken = false;
        weightPercent=1;
        resetImage();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
