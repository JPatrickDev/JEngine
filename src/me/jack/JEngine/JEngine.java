package me.jack.JEngine;

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
@SuppressWarnings("deprecation")
public class JEngine {


	public static TrueTypeFont font = null;
	
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	/**
	 * 
	 * @param game The game to run
	 * @param width The width of the screen
	 * @param height The height of the screen
	 */
	public static void start(Game game, int width, int height) {
		try {
			AppGameContainer agc = new AppGameContainer(game);
			agc.setDisplayMode(width, height, false);
			agc.setTargetFrameRate(60);
			JEngine.SCREEN_HEIGHT = height;
			JEngine.SCREEN_WIDTH = width;
			agc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Must be called before the render methods of the game, loads the font for the Menu buttons
	 */
	public static void loadFont(){
		Font f = new Font("Verdana", 0,45);
		font = new TrueTypeFont(f,true);
	}
}
