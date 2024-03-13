package engine.windows.GameLevel;

import engine.windows.GameWindows;
import engine.windows.common.Position;
import engine.windows.node.GameObject;
import engine.windows.node.Object.Taker;
import engine.windows.node.Object.Underground.Diamond;
import engine.windows.node.Object.Underground.Gold;
import engine.windows.node.Object.Underground.Rock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level {
    int target;
    Diamond diamond;
    Rock rock;
    Gold gold;
    Taker taker;
    List<Position> pos = new ArrayList<Position>();
    List<GameObject> listUObject = new ArrayList<GameObject>();
    public Level(Taker taker, int level, int diamond, int squareGold, int bigGold, int medGold, int smallGold, int bigRock, int smallRock, int bag) {
        super();
        this.taker = taker;
        this.matrixPosition();
        this.initTarget(level);
        this.initDiamond(diamond);
//------initGold------//
        this.initGold(squareGold, "square");
        this.initGold(bigGold, "big");
        this.initGold(medGold, "medium");
        this.initGold(smallGold, "small");
//------initRock------//
        this.initRock(bigRock, "big");
        this.initRock(smallRock, "small");

    }
    public void initTarget(int level){
        switch (level) {
            case 1:
                this.target = 650;
                break;
            case 2:
                this.target = 1500;
                break;
            case 3:
                this.target = 3000;
                break;
        }
    }
    public void matrixPosition() {
        for(Integer i = 0; i < 1400; i += 36) {
            for(Integer j = 350; j < 800; j += 32) {
                Position tmp = new Position(i, j);
                this.pos.add(tmp);
            }
        }
    }

    public void initDiamond(int amount) {
        if(amount == 0)
            return;
        while (amount != 0) {
            int length = this.pos.size();
            int flag = (int) (Math.random() * length);
            Position nPos = this.pos.get(flag);
            diamond = new Diamond(nPos, taker);
            int count = 0;
            int limit = 2;
            List<Position> bin = new ArrayList<Position>();
            bin.add(nPos);
            for (Position pair : this.pos) {
                for (int i = 1; i <= limit; ++i) {
                    for(int j = 1; j <= limit; ++j) {
                        if(pair.x == nPos.x + i*36 && pair.y == nPos.y + j*32) {
                            bin.add(pair);
                            count++;
                        }
                    }
                }
            }
            if(count == (limit*limit)) {
                listUObject.add(diamond);
                amount -= 1;
                this.pos.removeAll(bin);
            }
            else
                continue;;
        }
    }
    public void initGold(int amount, String type) {
        if(amount == 0)
            return;
        while (amount != 0) {
            int length = this.pos.size();
            int flag = (int) (Math.random() * length);
            Position nPos = this.pos.get(flag);
            gold = new Gold(type, nPos, taker);
            int count = 0;
            int limit = 1;
            if (type == "small") {
                limit = 2;
            } else if (type == "medium") {
                limit = 3;
            } else if (type == "big") {
                limit = 4;
            } else if (type == "square") {
                limit = 5;
            }
            List<Position> bin = new ArrayList<Position>();
            bin.add(nPos);
            for (Position pair : this.pos) {
                for (int i = 1; i <= limit; ++i) {
                    for(int j = 1; j <= limit; ++j) {
                        if(pair.x == nPos.x + i*36 && pair.y == nPos.y + j*32) {
                            bin.add(pair);
                            count++;
                        }
                    }
                }
            }
            if(count == (limit*limit)) {
                listUObject.add(gold);
                amount -= 1;
                this.pos.removeAll(bin);
            }
            else
                continue;;
        }
    }

    public void initRock(int amount, String type) {
        if(amount == 0)
            return;
        while (amount != 0) {
            int length = this.pos.size();
            int flag = (int) (Math.random() * length);
            Position nPos = this.pos.get(flag);
            rock = new Rock(type, nPos, taker);
            int count = 0;
            int limit = 3;
            List<Position> bin = new ArrayList<Position>();
            bin.add(nPos);
            for (Position pair : this.pos) {
                for (int i = 1; i <= limit; ++i) {
                    for(int j = 1; j <= limit; ++j) {
                        if(pair.x == nPos.x + i*36 && pair.y == nPos.y + j*32) {
                            bin.add(pair);
                            count++;
                        }
                    }
                }
            }
            if(count == (limit*limit)) {
                listUObject.add(rock);
                amount -= 1;
                this.pos.removeAll(bin);
            }
            else
                continue;;
        }
    }

    public void draw(Graphics g) {
        for(GameObject gameObject: listUObject) {
            gameObject.draw(g);
        }
    }

    public List<GameObject> getListUObject() {
        return listUObject;
    }

    public void update() {
        for(GameObject gameObject: listUObject) {
            gameObject.update();
        }
    }

    public int getTarget() {
        return target;
    }
}
