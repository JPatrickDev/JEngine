package me.jack.JEngine.AI.tests;

import me.jack.JEngine.AI.Entity.Entity;
import me.jack.JEngine.AI.Level.AILevel;
import org.newdawn.slick.geom.Rectangle;

/**
 * Author: Jack
 * Date: 23/07/13
 */
public class TestLevel implements AILevel{
    @Override
    public boolean canMove(int x, int y,Entity entity) {
        boolean can = true;
        can = !(x > 800 || x < 0 || y < 0 || y > 600);

        return can;
    }

    @Override
    public boolean canMove(Rectangle rect,Entity entity) {
        for(Rectangle r: AITESTGame.hitboxes){
            if(r.intersects(rect)){
                return false;
            }
        }
        return true;
    }
}
