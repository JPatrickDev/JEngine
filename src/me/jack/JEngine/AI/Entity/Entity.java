package me.jack.JEngine.AI.Entity;

import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Level.AILevel;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Author: Jack
 * Date: 19/07/13
 */
public abstract class Entity {

    private int x;
    private int y;
    private int w;
    private int h;

    private int moveSpeed = 0;

    private boolean dead = false;

    protected Behaviour currentBehaviour;

    public AILevel level;
    public Entity(int x, int y,int w,int h,int moveSpeed,AILevel level) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.moveSpeed = moveSpeed;
        this.level = level;
    }


    public abstract void update(int delta);

    public abstract void render(Graphics g);

    public void die() {
        this.dead = true;
    }

    public boolean isDead(){
        return this.dead;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }


    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void addX(int x){
        if(level.canMove(getNewHixBox(this.x + x,y)))
        this.x += x;
    }

    public void addY(int y){
        if(level.canMove(getNewHixBox(this.x,this.y + y)))
        this.y +=y;
    }

    public Rectangle getHitBox(){
        return new Rectangle(x,y,w,h);
    }

    public Rectangle getNewHixBox(int x,int y){
        return new Rectangle(x,y,w,h);
    }

    public int getMoveSpeed() {
        return this.moveSpeed;
    }
}
