package me.jack.JEngine.Util;

import org.newdawn.slick.*;


import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Utils {

	/**
	 * Checks if the 2 Colors are the same
	 * @param c1 The first color
	 * @param c2 The second color
	 * @return <code>true</code> if the colors are the same <code>false</code> if not
	 */
	public static boolean isSameColour(org.newdawn.slick.Color c1,org.newdawn.slick.Color c2){
		if(c1.r == c2.r){
			if(c1.g == c2.g){
				if(c2.b == c2.b){
					return true;
				}
			}
		}
		return false;
	}

    public static Rectangle rect(int x,int y, int w,int h){
        return new Rectangle(x,y,w,h);
    }


    public static Rectangle square(int x,int y, int w){
        return rect(x,y,w,w);
    }

    public static void play(String sound){
        SoundEngine.getInstance().play(sound);
    }

    public static void addSound(String key,Sound sound){
        SoundEngine.getInstance().addSound(key,sound);
    }


    public static void makeCollisionMap(){
        try {
            BufferedImage image = ImageIO.read(new File("map.png"));
            BufferedImage output = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int  clr   = image.getRGB(x, y);
                    int alpha = (clr >> 24) & 0xFF;
                    if(alpha != 0){
                        output.setRGB(x,y,java.awt.Color.BLACK.getRGB());
                    }
                }
            }
            ImageIO.write(output,"PNG" ,new File("collison_map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
