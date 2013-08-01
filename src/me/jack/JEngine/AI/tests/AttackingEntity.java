package me.jack.JEngine.AI.tests;

import me.jack.JEngine.AI.Behaviour.Follow.ConstantFollow;
import me.jack.JEngine.AI.Entity.Entity;
import me.jack.JEngine.AI.Level.AILevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Author: Jack
 * Date: 21/07/13
 */
public class AttackingEntity extends Entity {
    public AttackingEntity(int x, int y,AILevel level) {
        super(x, y,32,32,6,level);
       this.currentBehaviour = new ConstantFollow(this);
    this.currentBehaviour.setTarget(AITESTGame.player);
      //  this.currentBehaviour = new Wander(this,600);

        //this.currentBehaviour = new Patrol(this,getRandomPoint(new Rectangle(800,600)),getRandomPoint(new Rectangle(800,600)));

    }

    public Point getRandomPoint(Rectangle range) {
        int randomX = (int) getRandomInteger(range.x, range.x + range.width);
        int randomY = (int) getRandomInteger(range.y, range.y + range.height);
        return new Point(randomX, randomY);
    }

    public double getRandomInteger(int min, int max) {
        return min + Math.floor(Math.random() * (max + 1 - min));
    }


    @Override
    public void update(int delta) {
        this.currentBehaviour.update(delta);
        if(this.getHitBox().intersects(AITESTGame.player.getHitBox())){
            die();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(getX(),getY(),32,32);
        g.setColor(Color.white);

        this.currentBehaviour.renderDebug(g);
    }
}
