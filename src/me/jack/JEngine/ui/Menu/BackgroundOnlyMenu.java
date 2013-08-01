package me.jack.JEngine.ui.Menu;

import me.jack.JEngine.Util.ImageUtil;
import me.jack.JEngine.ui.Button.ArrowKeyButton;
import me.jack.JEngine.ui.Button.Button;
import me.jack.JEngine.ui.Button.ButtonListener;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Author: Jack
 * Date: 28/02/13
 */
public class BackgroundOnlyMenu extends Menu {

    Image bg = null;

    ButtonListener listener = null;

    @Override
    public void update(GameContainer arg0) {
        if (Mouse.isButtonDown(0)) {
            this.listener.onButtonActivate(new ArrowKeyButton("click", false, 0, 0, null));
        }
    }

    @Override
    public void render(Graphics g) {
        if (bg == null) {
            throw new NullPointerException("BG cannot be null");
        }
        g.drawImage(bg, 0, 0);
    }

    @Override
    public ButtonListener getListener() {
        return this.listener;
    }

    @Override
    public void setListener(ButtonListener l) {
        this.listener = l;
    }

    public void setBackground(String res) {
        this.bg = ImageUtil.loadImage(res);
    }
}
