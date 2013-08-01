package me.jack.JEngine.ui.Button;

import me.jack.JEngine.JEngine;

import me.jack.JEngine.ui.Menu.Menu;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Button {


    int x;
    int y;
	Menu parent;
    public String text;

	/**
	 *
	 * @param x The x of the button
	 * @param y The y of the button
	 * @param parent The parent @see Menu that the @see Button
	 */
	public Button(int x, int y, Menu parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

    public abstract void render(Graphics g);
    public abstract void update(GameContainer arg0);

}
