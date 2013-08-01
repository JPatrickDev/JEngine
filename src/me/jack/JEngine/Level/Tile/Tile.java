package me.jack.JEngine.Level.Tile;

import java.util.HashMap;

import me.jack.JEngine.Level.Level;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {

	public Image image;
	public int tId;

	public int sX;
	public int sY;
	public boolean solid = false;
	public String saveChar;
	static int pos = 0;
	public static HashMap<Integer, Tile> tileLookup = new HashMap<Integer, Tile>();

	public Tile(Image i, int sX, int sY, boolean solid) {
		this.solid = solid;
		this.tId = pos;
		this.image = i.getScaledCopy(i.getWidth(),i.getHeight());
		tileLookup.put(pos, this);
		pos++;
		this.sX = sX;
		this.sY = sY;
	}

	public void update(Level level, int x,int y){
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y);
	}
}
