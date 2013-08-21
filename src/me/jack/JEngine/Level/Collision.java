package me.jack.JEngine.Level;

import org.newdawn.slick.tiled.TiledMap;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Author: Jack
 * Date: 26/04/13
 */
public class Collision {

    private ArrayList<Rectangle> collisions = new ArrayList<Rectangle>();

    public void load(TiledMap map){
        for(int x = 0;x!= map.getWidth();x++){
            for(int y = 0;y!= map.getHeight();y++){
                if(map.getTileProperty(map.getTileId(x,y,map.getLayerCount()-1),"solid","false").equalsIgnoreCase("true")){
                    System.out.println("Solid@ " + x + " " + y);
                    collisions.add(new Rectangle(x *32,y*32,32,32));
                }
            }
        }
    }

    public boolean check(Rectangle player){
        for(Rectangle rect : collisions){
            if(player.intersects(rect)){
                return false;
            }
        }

        return true;
    }

}
