package me.jack.JEngine.Level.Editor;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import me.jack.JEngine.Level.Tile.Tile;

public class CreateTileset{

	private JPanel contentPane;
	private JTextField textField;
	JTextPane textPane;

	public static final String chars = "abcdefghijklmnopqrstuvqxyz1234567890";
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public CreateTileset() throws IOException {
		addText("JEngine tileset creator\ntype exit when finished\nload \"path to spritesheet\"\nadd tx ty solid\nsave PATH\nexit");
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String line = scanner.nextLine();
			if(!processCommand(line)){
				break;
			}
		}
scanner.close();
	}

	Tileset set = null;

	int pos = 0;
	public boolean processCommand(String cmd) throws IOException {
		if (cmd.startsWith("load")) {
			String[] split = cmd.split(" ");
			String path = split[1];
			int w = Integer.parseInt(split[2]);
			try {
				set = new Tileset(path,w);
				System.out.println("Tileset created");
			} catch (Exception e) {
				addText("ERROR:" + e.getMessage());
			}
			return true;
		}else if(cmd.startsWith("add")){
			try{
			String[] split = cmd.split(" ");
			int x = Integer.parseInt(split[1]);
			int y = Integer.parseInt(split[2]);
			boolean solid = Boolean.valueOf(split[3]);
			Tile tile = new Tile(set.sheet.getSprite(x, y), x, y, solid);
			tile.saveChar = CreateTileset.chars.charAt(pos) + "";
			pos++;
			set.tiles.add(tile);
			addText("Tile added:" + tile.toString());
			}catch(Exception e){
				if(e instanceof NullPointerException){
					addText("Error:" + e.getMessage() +  ". Did you load a spritesheet?");
				}
				else{
					addText("Error:" + e.getMessage());
				}
			}
			return true;
		}else if(cmd.startsWith("info")){
			if(set == null){
				addText("No tileset loaded");
			}
			else{
				addText("Tileset:" + set.spriteSheetPath + " WH:" + set.w);
				for(Tile t : set.tiles){
					addText("Tile:" + t.sX + ":" + t.sY + ":" + t.solid);
				}
			}
			return true;
		}else if(cmd.startsWith("exit")){
			addText("Exiting");
			return false;
		}else if(cmd.startsWith("save")){
			try{
			String path = cmd.substring(5);
			set.save(path);
			addText("Saved to " + path);
			}catch(Exception e){
				addText("Error:" + e.getMessage());
			}
			return true;
		}
		
		return true;
	}

	public void addText(String text) {
		System.out.println(text);
	}
}
