package me.jack.JEngine.AI.tests;

import me.jack.JEngine.AI.Entity.Entity;
import me.jack.JEngine.AI.Level.AILevel;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Author: Jack
 * Date: 19/07/13
 */
public class BasicEntity extends Entity {
    public BasicEntity(int x, int y, AILevel level) {
        super(x, y, 32, 32, 6, level);
    }

    @Override
    public void update(int delta) {
        this.currentBehaviour.update(delta);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            addY(-getMoveSpeed());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            addX(-getMoveSpeed());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            addY(getMoveSpeed());
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            addX(getMoveSpeed());
        }
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(getX(), getY(), 32, 32);
        g.setColor(Color.white);

    }
}
