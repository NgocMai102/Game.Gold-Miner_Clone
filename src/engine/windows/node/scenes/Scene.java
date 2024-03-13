package engine.windows.node.scenes;

import engine.windows.GameWindows;
import engine.windows.node.GameObject;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Scene {
    protected List<GameObject> listGameObject;
    protected GameWindows gameWindows;

    public Scene(GameWindows gameWindows) {
        this.gameWindows = gameWindows;
        this.listGameObject = new ArrayList<>();
    }

    public void draw(Graphics g) {
        for (GameObject gameObject : listGameObject) {
            gameObject.draw(g);
        }
    }

    public void update() {
        for (GameObject gameObject : listGameObject) {
            gameObject.update();
        }
    }
}
