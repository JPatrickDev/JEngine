package me.jack.JEngine.AI.Level;

import org.newdawn.slick.geom.Rectangle;

/**
 * Author: Jack
 * Date: 23/07/13
 */
public interface AILevel {

    public boolean canMove(int x,int y);
    public boolean canMove(Rectangle rect);
}
