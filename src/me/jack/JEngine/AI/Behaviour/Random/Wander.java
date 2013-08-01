package me.jack.JEngine.AI.Behaviour.Random;

import me.jack.JEngine.AI.AIUtils.MathsUtils;
import me.jack.JEngine.AI.AIUtils.Vector;
import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.awt.Point;
import java.awt.Rectangle;

public class Wander extends Behaviour {
    private int radius = 0;

    private Rectangle aoe = null;

    private Point target = null;

    private long delay = 500;
    private long waited = 0;
    private boolean waiting = false;

    public Wander(Entity owner, int w) {
        super(owner);
        if (w == 0) {
            throw new IllegalArgumentException("Radius cannot be 0!");
        }
        this.radius = w;
        this.aoe = new Rectangle(owner.getX() - (w / 2) + (owner.getW() / 2), owner.getY() - (w / 2) + (owner.getH() / 2), w, w);
    }
    Vector targetVector = null;
    @Override
    public void update(int delta) {
        int oX = getOwner().getX();
        int oY = getOwner().getY();
        this.aoe.x = oX - (radius / 2) + (getOwner().getW() / 2);
        this.aoe.y = oY - (radius / 2) + (getOwner().getH() / 2);

        if (target == null) {
            if (waited == 0) {
                waited += delta;
            } else if (waited >= delay) {
                waited = 0;
                pickTarget();
            } else {
                waited += delta;
            }
        } else {


            int tX = target.x;
            int tY = target.y;
            float speed = getOwner().getMoveSpeed();
            float dX = tX - oX;
            float dY = tY - oY;
            float dist = (float) Math.sqrt(dX * dX + dY * dY);
            float sX = speed * dX / dist;
            float sY = speed * dY / dist;

            getOwner().addX((int) (sX + 0.5));
            getOwner().addY((int) (sY + 0.5));
            if (getOwner().getHitBox().intersects(new org.newdawn.slick.geom.Rectangle(target.x - 16, target.y - 16, 32, 32))) {
                target = null;
            }
        }

    }

    private void pickTarget() {
        boolean goodPoint = false;
        while (!goodPoint) {
            Point r = getRandomPoint(aoe);
            if (getOwner().level.canMove(r.x, r.y)) {
                target = r;
                goodPoint = true;
                targetVector = MathsUtils.getVelocityToTarget(r.x,r.y,(int)getOwner().getHitBox().getCenterX(),(int)getOwner().getHitBox().getCenterY(),getOwner().getMoveSpeed());
            }

        }
    }

    public Point getRandomPoint(Rectangle range) {
        int randomX = (int) getRandomInteger(range.x, range.x + range.width);
        int randomY = (int) getRandomInteger(range.y, range.y + range.height);
        return new Point(randomX, randomY);
    }

    public double getRandomInteger(int min, int max) {
        return min + Math.floor(Math.random() * (max + 1 - min));
    }


    private Color debugColor = new Color(0, 215, 33, 128);

    @Override
    public void renderDebug(Graphics g) {

        g.setColor(debugColor);
     //   g.fillRect(aoe.x, aoe.y, aoe.width, aoe.height);
        g.setColor(Color.white);

        if (target != null)
            g.fillRect(target.x - 16, target.y - 16, 32, 32);
    }

}
