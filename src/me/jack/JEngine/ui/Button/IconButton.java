package me.jack.JEngine.ui.Button;

import me.jack.JEngine.JEngine;
import me.jack.JEngine.Util.ImageUtil;
import me.jack.JEngine.ui.Menu.Menu;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Author: Jack
 * Date: 28/02/13
 */
public class IconButton extends Button {

    Image icon = null;
    int textX;
    int textY;
    int width;
    int height;


    /**
     * @param text     The text to draw
     * @param selected Is the button initialy selected
     * @param x        The x of the button
     * @param y        The y of the button
     * @param parent   The parent @see Menu that the @see Button
     */
    public IconButton(String text, boolean selected, int x, int y, Menu parent, String iconRes) {
        super(x, y, parent);
        icon = ImageUtil.loadImage(iconRes);

        width = JEngine.SCREEN_WIDTH / 4;
        height = JEngine.SCREEN_HEIGHT / 10;
        System.out.println(height);
        if (icon.getHeight() != height) {
            icon = null;
            throw new IllegalArgumentException("The icon must the same height as the button");
        }
        textX = x + (JEngine.font.getWidth(text) / 2);
        textY = y + (JEngine.font.getHeight(text) /4);
        this.text = text;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(icon, x, y);
        g.fillRect(x + icon.getWidth(), y, width, height);
        g.setColor(Color.white);
        g.drawString(text,textX,textY);
    }

    @Override
    public void update(GameContainer arg0) {

    }


}
