package me.jack.JEngine.AI.Behaviour.Follow;

import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Behaviour.Random.Wander;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Author: Jack
 * Date: 29/07/13
 */
public class ConstantFollow extends Behaviour {
;

    public ConstantFollow(Entity owner) {
        super(owner);

    }
    private Wander tempEffect = null;
    @Override
    public void update(int delta) {
        int oX = getOwner().getX();
        int oY = getOwner().getY();

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

    }
}
