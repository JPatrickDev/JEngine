package me.jack.JEngine.ui;

import me.jack.JEngine.JEngine;

import org.newdawn.slick.Graphics;

public abstract class Popup{

	public int width;
	public int height;
	
	 public int x;
	 public int y;
	
	public Popup(int width,int height,PopupPosition pos){
		if(width == 0){
			throw new IllegalArgumentException("Width cannot be 0");
		}	if(height == 0){
			throw new IllegalArgumentException("Height cannot be 0");
		}
		this.width = width;
		this.height = height;
		int screenWidth = JEngine.SCREEN_WIDTH;
		int screenHeight = JEngine.SCREEN_HEIGHT;
		
		if(pos == PopupPosition.CENTERED){
			this.x = (screenWidth/2) - (width/2);
			this.y = (screenHeight/2) - (height/2);
		}else if(pos == PopupPosition.BOTTOM){
			this.x = (screenWidth/2) - (width/2);
			this.y = screenHeight - 100;
		}else if(pos == PopupPosition.TOP){
			this.x = (screenWidth/2) - (width/2);
			this.y = 25;
		}


	}
	
	public abstract void render(Graphics g);
	public abstract void update();
}


