/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;


import engine.windows.node.scenes.Scene;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.Stack;

/**
 * @author Tdh4vn
 */
public class GameWindows extends Frame implements Runnable {

    private static final int UPDATE_PER_SECOND = 60;
    Stack<Scene> sceneStack;

    private Image image;
    private Graphics second;

    Stack<KeyListener> keyListenerStack;
    Stack<MouseListener> mouseListenerStack;

    public GameWindows() {
        super();
        sceneStack = new Stack<>();
        keyListenerStack = new Stack<>();
        mouseListenerStack = new Stack<>();
        this.setSize(1440, 800);
        this.setTitle("GoldMiner");
        this.setFocusable(true);
        this.setVisible(true);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });
    }


    public void update(Graphics g) {
        if (!sceneStack.isEmpty()) {
            sceneStack.peek().update();
        }
        drawBufferImage(g);
        if(!keyListenerStack .isEmpty())
            this.addKeyListener(keyListenerStack.peek());
        if(!mouseListenerStack.isEmpty())
            this.addMouseListener(mouseListenerStack.peek());
    }

    private void drawBufferImage(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            //Tạo một 1 graphics ẩn
            second = image.getGraphics();
            //Lấy graphics ẩn
        }
        //Paint buffer image
        paint(second);
        g.drawImage(image, 0, 0, null);
    }


    public Stack<Scene> getSceneStack() {
        return sceneStack;
    }

    public Stack<KeyListener> getKeyListenerStack() {return keyListenerStack;}

    public Stack<MouseListener> getMouseListenerStack() {return mouseListenerStack;}

    @Override
    public void paint(Graphics g) {
        if (!sceneStack.isEmpty()) {
            sceneStack.peek().draw(g);
        }
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000 / UPDATE_PER_SECOND);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        Thread mainThread = new Thread(this);
        mainThread.start();
    }
}
