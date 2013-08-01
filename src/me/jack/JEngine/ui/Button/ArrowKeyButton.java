package me.jack.JEngine.ui.Button;

import me.jack.JEngine.JEngine;
import me.jack.JEngine.ui.Menu.Menu;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Author: Jack
 * Date: 01/03/13
 */
public class ArrowKeyButton extends Button{

    public boolean selected;

    /**
     * @param text     The text to draw
     * @param selected Is the button initialy selected
     * @param x        The x of the button
     * @param y        The y of the button
     * @param parent   The parent @see Menu that the @see Button
     */
    public ArrowKeyButton(String text, boolean selected, int x, int y, Menu parent) {
        super(x, y, parent);
        this.text= text;
        this.selected = selected;
    }

    /**
     *
     * @param g The graphics object
     */
    @Override
    public void render(Graphics g) {
        int drawX = x - (JEngine.font.getWidth(text) / 2);
        int drawY = y - (JEngine.font.getHeight(text));
        if (selected) {
            g.setColor(Color.white);
        }
        g.drawString(text, drawX, drawY);
        g.setColor(Color.gray);
    }

    private boolean debug = true;

    /**
     * Called to update the button
     */
    @Override
    public void update(GameContainer arg0) {
        if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && selected) {

            if (debug){
                System.out.println("Selected");
            }
            parent.getListener().onButtonActivate(this);
        }
    }
}
