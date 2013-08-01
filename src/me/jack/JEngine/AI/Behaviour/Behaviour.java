package me.jack.JEngine.AI.Behaviour;

import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Graphics;

/**
 * Author: Jack
 * Date: 21/07/13
 */
public abstract class Behaviour {


    private Entity target;
    private Entity owner;

    public Behaviour(Entity owner){
        this.owner = owner;
    }

    public abstract void update(int delta);

    public abstract void renderDebug(Graphics g);
    public void setTarget(Entity e){
        this.target = e;
    }
    public Entity getTarget(){
        return this.target;
    }

    public Entity getOwner(){
        return this.owner;
    }
}
