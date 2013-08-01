package me.jack.JEngine.Level.Editor;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import me.jack.JEngine.JEngine;
import me.jack.JEngine.Level.Level;
import me.jack.JEngine.Level.Tile.Tile;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Editor extends BasicGame {

	Level level = null;

	Tile selected = null;

	public Editor(String title) {
		super(title);
	}

	public static void main(String[] args) {
		JEngine.start(new Editor("JEngineMapEditor"), 800, 600);
	}

	int brushSize = 1;

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		if (zoomed)
			arg1.scale(0.2f, 0.2f);
		else
			arg1.scale(1, 1);
		level.render(arg1);
		int mX = arg0.getInput().getMouseX();
		int mY = arg0.getInput().getMouseY();
		arg1.drawImage(selected.image, mX, mY);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		try {

			setUp();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * Tileset set = null; try { set = Tileset.load("tileset.txt"); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } this.level = new Level(1920,1080, set,2);
		 */
		this.selected = level.getTileAt(0, 0);
	}

	public void setUp() throws FileNotFoundException, SlickException {
		String wH = JOptionPane
				.showInputDialog("Enter the width and the height of the map like so W:H");
		String[] split = wH.split(":");
		Dimension dim = new Dimension(Integer.parseInt(split[0]),
				Integer.parseInt(split[1]));
		int t = JOptionPane.showOptionDialog(null,
				"Do you want to create a Tileset or use an existing one?",
				"Tileset", 0, 3, null, new Object[] { "Create", "Existing" },
				"Create");
		if (t == 0) {
			createNewTileset();
		}
		String path = JOptionPane.showInputDialog("Enter path to Tileset");
		Tileset set = Tileset.load(path);
		this.level = new Level(dim.width, dim.height, set, 2);
		this.selected = level.getTileAt(0, 0);
	}

	public void createNewTileset() {
		try {
			new CreateTileset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int px;
	int py;

	int selectedPos = 0;

	boolean zoomed = false;

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		level.camera.centerOnPlayer(px, py, level.width, level.height);
		if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
			try {
				this.saveMap();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			this.brushSize = 1;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			this.brushSize = 2;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			this.brushSize = 3;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			this.brushSize = 4;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
			this.brushSize = 5;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
			this.brushSize = 6;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_7)) {
			this.brushSize = 7;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_8)) {
			this.brushSize = 8;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
			this.brushSize = 9;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
			zoomed = true;
		} else {
			zoomed = false;
		}
		if (zoomed)
			return;
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			py -= 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			px -= 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			py += 10;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			px += 10;
		}
		if (Mouse.isButtonDown(0)) {
			int mX = arg0.getInput().getMouseX();
			int mY = arg0.getInput().getMouseY();

			int x = (mX + level.camera.x);
			int y = (mY + level.camera.y);
			int tX = x / level.tW;
			int ty = y / level.tW;
			level.setTileAt(tX, ty, selected.tId);
		}
		int mouse = Mouse.getDWheel();
		if (mouse < 0) {
			if (selectedPos != 0) {
				selectedPos--;
				this.selected = level.tileset.tiles.get(selectedPos);
			}
		} else if (mouse > 0) {
			if (selectedPos != level.tileset.tiles.size() - 1) {
				selectedPos++;
				this.selected = level.tileset.tiles.get(selectedPos);
			}
		}
	}

	public void saveMap() throws IOException {
		String savePath = JOptionPane
				.showInputDialog("Please enter the save path");
		File file = new File(savePath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(level.width + ":" + level.height);
		writer.newLine();
		for (int y = 0; y != level.height; y++) {
			for (int x = 0; x != level.width; x++) {
				Tile t = level.getTileAt(x, y);
				writer.write(t.saveChar);
			}
			writer.newLine();
		}
		writer.close();
	}

	
}
