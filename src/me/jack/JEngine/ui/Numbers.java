package me.jack.JEngine.ui;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Numbers {
static SpriteSheet numbers;

public static void loadNumbers(){
	try {
		numbers = new SpriteSheet("/res/number.png",7,7);
	} catch (SlickException e) {
		e.printStackTrace();
	}
}
static HashMap<Integer, Image> numberMap = new HashMap<Integer,Image>();

public static Image getNumber(int no){
	if(numberMap.containsKey(no)){
		return numberMap.get(no);
	}else{
		numberMap.put(no, numbers.getSprite(no, 0).getScaledCopy(3));
		return numberMap.get(no);
	}	
}
}
