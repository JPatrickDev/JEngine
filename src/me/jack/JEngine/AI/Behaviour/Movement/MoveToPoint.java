package me.jack.JEngine.AI.Behaviour.Movement;

import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Point;

/**
 * Author: Jack
 * Date: 01/08/13
 */
public class MoveToPoint extends Behaviour {

    private Point point1 = null;


    public MoveToPoint(Entity owner, Point p1) {
        super(owner);
        this.point1 = p1;

    }

    @Override
    public void update(int delta) {
        System.out.println("Update");
        Rectangle hitbox = getOwner().getHitBox();
        hitbox.setWidth(4);
        hitbox.setHeight(4);
        moveToOne();
        if (point1 == null)
            return;
        Rectangle point = new Rectangle((float) point1.getX(), (float) point1.getY(), 4, 4);
        if (point.intersects(hitbox)) {
            point1 = null;

        }


    }

    private void moveToOne() {
        if (point1 == null)
            return;
        int tX = (int) point1.getX();
        int tY = (int) point1.getY();

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
        renderDebug(g, 0,0);
    }

    @Override
    public void renderDebug(Graphics g, int x, int y) {
        if (point1 == null)
            return;
        Rectangle point1rect = new Rectangle(((float) point1.getX() - 4) - x, ((float) point1.getY() - 4) -y, 8, 8);
        g.setColor(Color.pink);
        g.fill(point1rect);
        g.setColor(Color.white);
    }

    public void setTarget(Point p) {
        this.point1 = p;
    }

    public Point getPoint() {
        return point1;
    }
}
