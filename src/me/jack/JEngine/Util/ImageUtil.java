package me.jack.JEngine.Util;

import java.awt.Dimension;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageUtil {

	/**
	 * Loads an Image
	 * @param res The filepath to load
	 * @return The loaded image or null if there was an error
	 */
	public static Image loadImage(String res){
		Image i = null;
		try {
			i= new Image(res);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * Gets the width and height of an Image
	 * @param i The image to get the size of
	 * @return The Dimension of the Image
	 */
	public static Dimension getSize(Image i){
		Dimension d = new Dimension(i.getWidth(),i.getHeight());
		return d;
	}
	
	/**
	 * Gets the width and height of an Image for a String path
	 * @param res The filepath of the image
	 * @return The Dimension of the Image
	 */
	public static Dimension getSize(String res){
		return getSize(loadImage(res));
	}
	
	   public static String toHex(int r, int g, int b) {
		    return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
		  }

		  private static String toBrowserHexValue(int number) {
		    StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		    while (builder.length() < 2) {
		      builder.append("0");
		    }
		    return builder.toString().toUpperCase();
		  }
}
