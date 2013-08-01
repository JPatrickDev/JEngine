package me.jack.JEngine.ui;

import java.awt.Rectangle;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * 
 * @author Jack
 *NOT FINISHED, DO NOT USE
 */
public class TextBox {
	String content = "test";
	String allowedChars = "abcdefghijklmnopqrstuvwxyz123456789_-";
	boolean acceptingInput = false;
	boolean has = false;
	int maxLength = 10;
	int x;
	int y;
	public TextBox(int x,int y){
		this.x = x;
	}
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(x, y, 200, 64);
		g.setColor(Color.white);
		g.drawString(content,x,y);
		if(acceptingInput){
			g.setColor(Color.red);
			if(!has){
				g.setColor(Color.red);
				g.drawRect(x, y, 200, 64);
				has  = true;
			}
			else{
				g.drawRect(x, y, 200, 64);
				has = false;
			}
		}
	}
	long last = System.currentTimeMillis();
	public void update(GameContainer arg0){
		int mX = arg0.getInput().getMouseX();
		int mY = arg0.getInput().getMouseY();
		Rectangle mR =  new Rectangle(mX,mY,32,32);
		Rectangle tbR = new Rectangle(x,y,200,64);
		if(tbR.intersects(mR)){
			acceptingInput = true;
		}
		else{
			acceptingInput= false;
		}
		if(acceptingInput){
		
		}
	}
}
