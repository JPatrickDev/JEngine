package me.jack.JEngine.AI.Behaviour.Movement;

import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Point;

/**
 * Author: Jack
 * Date: 24/07/13
 */
public class Patrol extends Behaviour{

    private Point point1 = null;
    private Point point2 = null;

    private int movingTo = 1;

    private long waitFor = 1000;
    private long waited = 0;
    private boolean waiting = false;
    public Patrol(Entity owner,Point p1,Point p2) {
        super(owner);
        this.point1 = p1;
        this.point2 = p2;
    }

    @Override
    public void update(int delta) {
          if(movingTo == 1){
            moveToOne();
          }else{
                moveToTwo();
          }

        Rectangle hitbox=  getOwner().getHitBox();
        if(movingTo==1){
            Rectangle point = new Rectangle((float)point1.getX() - 4,(float)point1.getY()-4,8,8);
            if(point.intersects(hitbox)){
                if(!waiting){
                    waiting = true;
                }
                if(waiting){
                    waited+=delta;
                    if(waited >= waitFor){
                        movingTo=2;
                        waiting = false;
                        waited= 0;
                    }
                }

            }
        }else{
            Rectangle point = new Rectangle((float)point2.getX() - 4,(float)point2.getY()-4,8,8);
            if(point.intersects(hitbox)){
                if(!waiting){
                    waiting = true;
                }
                if(waiting){
                    waited+=delta;
                    if(waited >= waitFor){
                        movingTo=1;
                        waiting = false;
                        waited= 0;
                    }
                }
            }
        }
    }

    private void moveToOne(){
    if(waiting)
        return;
        int tX = (int) point1.getX();
        int tY = (int)point1.getY();

        int oX = getOwner().getX();
        int oY = getOwner().getY();


        float speed = getOwner().getMoveSpeed();
        float dX = tX - oX;
        float dY = tY - oY;
        float dist = (float) Math.sqrt(dX * dX + dY * dY);
        float sX = speed * dX / dist;
        float sY = speed * dY / dist;

        getOwner().addX((int) (sX + 0.5));
        getOwner().addY((int) (sY + 0.5));
    }
    private void moveToTwo(){

        if(waiting)
            return;
        int tX = (int) point2.getX();
        int tY = (int)point2.getY();

        int oX = getOwner().getX();
        int oY = getOwner().getY();


        float speed = getOwner().getMoveSpeed();
        float dX = tX - oX;
        float dY = tY - oY;
        float dist = (float) Math.sqrt(dX * dX + dY * dY);
        float sX = speed * dX / dist;
        float sY = speed * dY / dist;

        getOwner().addX((int) (sX + 0.5));
        getOwner().addY((int) (sY + 0.5));
    }

    @Override
    public void renderDebug(Graphics g) {
        Rectangle point1rect = new Rectangle((float)point1.getX() - 4,(float)point1.getY()-4,8,8);
        Rectangle point2rect = new Rectangle((float)point2.getX() - 4,(float)point2.getY()-4,8,8);
        g.setColor(Color.pink);
        g.fill(point1rect);
        g.setColor(Color.cyan);
        g.fill(point2rect);
        g.setColor(Color.white);
    }
}
