package me.jack.JEngine.AI.Behaviour.Follow;

import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Behaviour.Random.Wander;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

/**
 * Author: Jack
 * Date: 21/07/13
 */
public class RadiusFollow extends Behaviour{

    private int radius = 0;

    private Circle aoe = null;

    public RadiusFollow(Entity owner,int radius) {
        super(owner);
        if(radius == 0){
            throw new IllegalArgumentException("Radius cannot be 0!");
        }
        this.radius = radius;
        this.aoe = new Circle(owner.getX()+(owner.getW()/2),owner.getY() + (owner.getH()/2),radius);
    }
    private Wander tempEffect = null;
    @Override
    public void update(int delta) {
        int oX = getOwner().getX();
        int oY = getOwner().getY();
        this.aoe.setCenterX(oX + (getOwner().getW()/2));
        this.aoe.setCenterY(oY + (getOwner().getH()/2));

        if(!getTarget().getHitBox().intersects(aoe)){
            if(tempEffect == null){
                tempEffect = new Wander(getOwner(),radius);
            }else{
                tempEffect.update(delta);
            }
            return;
        }
        tempEffect = null;
        Entity target = this.getTarget();
        int tX = target.getX();
        int tY = target.getY();


        float speed = getOwner().getMoveSpeed();
        float dX = tX - oX;
        float dY = tY - oY;
        float dist = (float) Math.sqrt(dX * dX + dY * dY);
        float sX = speed * dX / dist;
        float sY = speed * dY / dist;
        getOwner().addX((int) (sX + 0.5));
        getOwner().addY((int) (sY + 0.5));
    }

    private Color debugColor = new Color(0,255,33,128);
    @Override
    public void renderDebug(Graphics g) {
        if(tempEffect == null){
        g.setColor(debugColor);
        g.fill(aoe);
        g.setColor(Color.white);
        }else{
         tempEffect.renderDebug(g);
        }
    }
}
