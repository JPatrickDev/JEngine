package me.jack.JEngine.ui.Menu;

import me.jack.JEngine.ui.Button.ButtonListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Author: Jack
 * Date: 28/02/13
 */
public abstract class Menu {

    public abstract void update(GameContainer arg0);
    public abstract void render(Graphics g);
    public abstract ButtonListener getListener();
    public abstract void setListener(ButtonListener l);
}
