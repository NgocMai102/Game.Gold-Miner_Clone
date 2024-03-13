package engine.windows.node.scenes;

import engine.windows.GameLevel.Level;
import engine.windows.GameWindows;
import engine.windows.Score;
import engine.windows.common.Animation;
import engine.windows.common.Position;
import engine.windows.node.GameObject;
import engine.windows.node.Object.Human;
import engine.windows.node.Object.Rope;
import engine.windows.node.Object.Taker;
import engine.windows.node.background.GameBackground;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class GameScene extends Scene{
    //------MainObject------//
    GameBackground gameBackground;
    Taker taker;
    Rope rope;
    Human human;

    //-------Exploding------//
    boolean throwingBomb;
    ArrayList<BufferedImage> explodeImages;
    Stack<Animation> animationStack;
    Position explodingPosition = new Position(0,0);
    //------IDK------//
    KeyListener keyListener;
    Position position = new Position(0,0);
    //------ Level -------//
    Vector<Level> levelVector;
    Level level1,level2,level3,level4,level5;

    int levelPointer = 0;
    //------Number------//
    int time = 60;
    int tick = 0;
    int money = 0;
    int boom;
    Score moneyScore, timeScore,bombNum;
    //------End------//

    public GameScene(GameWindows gameWindows) {
        super(gameWindows);
        this.initTaker();
        this.initLevel(this.taker);
        this.initBackground();
        this.initGameObj();
        this.addKeyListener();
        this.initScore();
        this.initExplodeAnimation();
    }
    public void initExplodeAnimation(){
        explodeImages = new ArrayList<>();
        try {
            explodeImages.add(ImageIO.read(new File("Resources/Boom/boom2.png")));
            explodeImages.add(ImageIO.read(new File("Resources/Boom/boom1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.animationStack = new Stack<>();
    }
    public void initScore(){
        timeScore = new Score(time,new Position(1300,65));
        moneyScore = new Score(money, new Position(1300,115));
        bombNum = new Score(boom,new Position(955,100));
    }

    public void initLevel(Taker taker) {
        level1 = new Level(taker, 1, 0, 2, 0, 3, 5, 2 ,2, 0);
        level2 = new Level(taker, 2, 1,1, 1, 2, 5,2,3,1);
        level3 = new Level(taker,3, 2,3,1,2,4,3,1,2);
        level4 = new Level(taker, 4,5,0,0,0,7,4,4,0);
        level5 = new Level(taker, 5,3, 2,3,3,5,3,2,2);
        levelVector = new Vector<>();
        levelVector.add(level1);levelVector.add(level2);levelVector.add(level3);levelVector.add(level4);levelVector.add(level5);
    }

    public void initBackground() {
        gameBackground = new GameBackground();
        human = new Human(taker);
    }

    public void initTaker() {
        taker = new Taker(new Position(700,150));
        rope = new Rope(new Position(700, 200), taker);
        boom = 5;
    }

    public void initGameObj() {
        listGameObject = new ArrayList<>();
        listGameObject.add(rope);
        listGameObject.add(taker);
        for(GameObject gameObject: levelVector.get(levelPointer).getListUObject()) {
            listGameObject.add(gameObject);
        }
    }

    public void addKeyListener() {
        keyListener = new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }
            public void keyPressed(KeyEvent e) {

            }
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){

                    case KeyEvent.VK_DOWN:
                        if(taker.isOscillate()) {
                            taker.setOscillate(false);
                            taker.setThrowing(true);
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(taker.isPulling()==true&& boom > 0&&taker.isTaken()==true){
                            boom--;
                            bombNum.setN(boom);
                            explodingPosition = new Position(taker.getPosition().x,taker.getPosition().y);
                            Exploding();
                            taker.reset();
                            taker.setPrice(0);
                        }
                        break;
                }
            }
        };
    }

    @Override
    public void draw(Graphics g) {
        gameBackground.draw(g);
        human.draw(g);
        super.draw(g);
        drawNum(g);
        drawExploitation(g,explodingPosition);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    private void removeDestroyedGameObjects() {
        List<GameObject> destroyedObjects = new ArrayList<>();
        for (GameObject gameObject : listGameObject) {
            if (gameObject.getStatus()) {
                destroyedObjects.add(gameObject);
            }
        }
        listGameObject.removeAll(destroyedObjects);
    }

    void checkCollide() {
        for (int i = 0; i < listGameObject.size(); i++) {
            for (int j = i + 1; j < listGameObject.size(); j++) {
                GameObject gameObjectA = listGameObject.get(i);
                GameObject gameObjectB = listGameObject.get(j);
                if (gameObjectA.isCollide(gameObjectB)) {
                    gameObjectA.collideWith(gameObjectB);
                    gameObjectB.collideWith(gameObjectA);
                }
            }
        }
    }

    public void update() {
        super.update();
        checkCollide();
        removeDestroyedGameObjects();
        tickRaise();
        timeCount();
        isTaken();
        finishAnimation();
    }
    //------Bộ đếm thời gian------//
    public void timeCount(){
        if(tick%60==0){
            this.time -=1;
            this.timeScore.setN(time);
        }
    }
    public void tickRaise(){
        this.tick +=1;
    }
    //-------Kiểm tra đã gắp thành công------//
    public void isTaken(){
        if(taker.isOscillate()){
            this.money+=taker.getPrice();
            this.moneyScore.setN(money);
            taker.setPrice(0);
        }
    }
    //------ Vẽ số ------//
    public void drawNum(Graphics g){
        moneyScore.draw(g);
        timeScore.draw(g);
        bombNum.draw(g);
    }
    //------ Explode ------//
    public void Exploding(){
        animationStack.add(new Animation(300,explodeImages,false));
    }
    public void drawExploitation(Graphics g,Position position){
        BufferedImage image = null;
        if(!animationStack.isEmpty()) {
            image = animationStack.peek().getCurrentImage();
            if(image!=null)
                g.drawImage(image,(int)position.x,(int)position.y,null);
        }
    }
    public void finishAnimation(){
        if(!animationStack.isEmpty()) {
            if (animationStack.peek().isFinished()) {
                animationStack.pop();
                explodingPosition = new Position(0,0);
            }
        }
    }
}
