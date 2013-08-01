package me.jack.JEngine.Level;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import me.jack.JEngine.JEngine;
import me.jack.JEngine.Level.Editor.Tileset;
import me.jack.JEngine.Level.Tile.Tile;

import org.newdawn.slick.Graphics;

public class Level {

	public int[] tiles;
	public int width;
	public int height;
	public Tileset tileset;
	public Camera camera;
	public int tW = 0;
	public Level(int width,int height,Tileset set,int scale){
		this.tiles = new int[width*height];
		this.width = width;
		this.height = height;
		this.tileset = set;
		if(scale!= 0){
		this.tW = set.w * scale;
			for(Tile t : set.tiles){
				t.image = t.image.getScaledCopy(t.image.getWidth() * scale, t.image.getHeight()*scale);
			}
		}
		camera = new Camera(0,0,JEngine.SCREEN_WIDTH,JEngine.SCREEN_HEIGHT,tW);
	this.screen	 =  new Rectangle(0,0,JEngine.SCREEN_WIDTH,JEngine.SCREEN_HEIGHT);
	}
	
	public Tile getTileAt(int x,int y){
		return tileset.tiles.get(tiles[x+y*width]);
	}
	
	public void setTileAt(int x,int y,int t){
		tiles[x+y*width] = t;
	}
	Rectangle screen = null;
	public void render(Graphics g){
		for(int x = 0;x!=width;x++){
			for(int y = 0;y!= height;y++){
				if(new Rectangle((x*tW) - camera.x, (y * tW) - camera.y,this.tW,this.tW).intersects(screen)){
				Tile t = getTileAt(x,y);
				t.render(g, (x*tW) - camera.x, (y * tW) - camera.y);
				}
			}
		}
	}
	
	public static Level loadMap(Tileset set, String res, int scale)
			throws FileNotFoundException {
		File file = new File(res);
		Scanner scanner = new Scanner(file);
		String[] dim = scanner.nextLine().split(":");
		int width = Integer.parseInt(dim[0]);
		int height = Integer.parseInt(dim[1]);
		Level level = new Level(width, height, set, scale);

		int x = 0;
		int y = 0;
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			for (x = 0; x != width; x++) {
				String chars = line.charAt(x) + "";
				level.setTileAt(x, y, set.getFromChar(chars).tId);
			}
			x = 0;
			y++;
		}
		scanner.close();
		return level;
	}
	
}
