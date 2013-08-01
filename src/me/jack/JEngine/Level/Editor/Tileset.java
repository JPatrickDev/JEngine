package me.jack.JEngine.Level.Editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import me.jack.JEngine.Level.Tile.Tile;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Tileset {
	
		 

	public ArrayList<Tile> tiles = new ArrayList<Tile>();
	String spriteSheetPath = "";
	SpriteSheet sheet= null;
	public int w;
	
	static int[] colors = new int[800];
	static{
		for(int i = 0;i!=800;i++){
			colors[i] = new Random().nextInt();
		}
		
	}
	public Tileset(String sheet,int width) throws SlickException{
		this.sheet= new SpriteSheet(sheet,width,width);
		this.spriteSheetPath = sheet;
		this.w = width;
	}
	public Tileset(SpriteSheet sheet){
		this.sheet = sheet;
		this.w = sheet.getHorizontalCount() / sheet.getWidth();
		this.spriteSheetPath = sheet.getResourceReference();
	}
	public void save(String filePath) throws IOException{
		File file = new File(filePath);
		BufferedWriter w = new BufferedWriter(new FileWriter(file));
		w.write(spriteSheetPath);
		w.newLine();
		w.write("" + this.w);
		w.newLine();
		for(Tile tile : tiles){
			w.write(tile.sX + ":" + tile.sY + ":" + String.valueOf(tile.solid) + ":" + tile.saveChar);
			w.newLine();
		}
		w.close();
	}
	
	public Tile getFromChar(String chars){
		for(Tile t : this.tiles){
			if(t.saveChar.equalsIgnoreCase(chars)){
				return t;
			}
		}
		return null;
	}
	public static Tileset load(String filePath) throws FileNotFoundException, SlickException{
		File file =new File(filePath);
		Scanner scanner = new Scanner(file);
		String path = scanner.nextLine();
		int w = Integer.parseInt(scanner.nextLine());
		Tileset t = new Tileset(path,w);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] split = line.split(":");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			boolean solid = Boolean.parseBoolean(split[2]);
		String sChar = split[3];
			Tile tile = new Tile(t.sheet.getSprite(x, y), x,y, solid);
			tile.saveChar = sChar;
			t.tiles.add(tile);
		}
		scanner.close();
		return t;
	}
}
