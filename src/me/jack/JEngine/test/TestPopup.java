package me.jack.JEngine.test;

import me.jack.JEngine.ui.Popup;
import me.jack.JEngine.ui.PopupPosition;

import org.newdawn.slick.Graphics;

public class TestPopup extends Popup{

	public TestPopup() {
		super(100, 50, PopupPosition.TOP);

	}

	@Override
	public void render(Graphics g) {
		g.drawString("TEST", x + (this.width/2), y + (this.height/2));
	}

	@Override
	public void update() {
	
	}


}
