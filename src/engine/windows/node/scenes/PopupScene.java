package engine.windows.node.scenes;

import engine.windows.GameWindows;
import engine.windows.node.background.PopUp;

import java.awt.*;

public class PopupScene extends Scene{
    PopUp popUp;
    public PopupScene(GameWindows gameWindows) {
        super(gameWindows);
        popUp = new PopUp();
    }

    @Override
    public void draw(Graphics g) {
        popUp.draw(g);
        super.draw(g);
    }
}
