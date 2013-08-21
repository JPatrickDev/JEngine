package me.jack.JEngine.AI.Behaviour.Follow;

import me.jack.JEngine.AI.ASTAR.Map;
import me.jack.JEngine.AI.ASTAR.Node;
import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Behaviour.Movement.CompletePath;
import me.jack.JEngine.AI.Behaviour.Random.Wander;
import me.jack.JEngine.AI.Entity.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jack
 * Date: 29/07/13
 */
public class ConstantFollow extends Behaviour {

    private int tileSize;
    private boolean useAStar;
    private Map aStarMap;
    private List<Node> path;
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

    if(!useAStar){
        float speed = getOwner().getMoveSpeed();
        float dX = tX - oX;
        float dY = tY - oY;
        float dist = (float) Math.sqrt(dX * dX + dY * dY);
        float sX = speed * dX / dist;
        float sY = speed * dY / dist;
        getOwner().addX((int) (sX + 0.5));
        getOwner().addY((int) (sY + 0.5));
    }else{
        if(path == null){

        path =  aStarMap.findPath((getOwner().getX()/tileSize), (getOwner().getY()/tileSize), (getTarget().getX()/tileSize),(getTarget().getY()/tileSize));
        new CompletePath(new ArrayList<Node>(path),getOwner(),tileSize);
           System.out.println("ConstantFollow: Found path to target");
        }
        }
    }

    private Color debugColor = new Color(0,255,33,128);
    @Override
    public void renderDebug(Graphics g) {

       renderDebug(g,0,0);
    }

    @Override
    public void renderDebug(Graphics g, int x,int y) {

        g.fillRect(getTarget().getX() - x,getTarget().getY() - y,getTarget().getW(),getTarget().getH());
        if(path == null)return;
        for (Node n : path) {
            g.setColor(Color.orange);
            g.fillRect((n.getxPosition()*64) - x, (n.getyPosition()*64) - y, 64, 64);

        }
    }
    public void setTileSize(int size){
        this.tileSize = size;
    }
    public void setUseAStar(boolean useAStar,Map aStarMap){
        this.useAStar = useAStar;
        this.aStarMap = aStarMap;
    }
}
