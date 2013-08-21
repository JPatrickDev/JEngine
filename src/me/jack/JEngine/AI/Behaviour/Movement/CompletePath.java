package me.jack.JEngine.AI.Behaviour.Movement;

import me.jack.JEngine.AI.ASTAR.Node;
import me.jack.JEngine.AI.Behaviour.Behaviour;
import me.jack.JEngine.AI.Entity.Entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author: Jack
 * Date: 01/08/13
 */
public class CompletePath{

    public static final CopyOnWriteArrayList<CompletePath> paths = new CopyOnWriteArrayList<CompletePath>();
    private ArrayList<Node> path;
    private Entity e;
    private int i = 0;
    private Behaviour old = null;
    protected boolean remove = false;
    private int tileSize;
    public CompletePath(ArrayList<Node> path,Entity e, int tileSize){
        this.path= path;
        this.e = e;
        this.old = e.getBehaviour();
        e.setBehaviour(new MoveToPoint(e,null));
        paths.add(this);
        this.tileSize = tileSize;
    }

    public void step(){
        if((i +1)> path.size()){
            remove = true;
            return;
        }
        Node next = path.get(i++);
        Point p = new Point(next.getxPosition()*tileSize,next.getyPosition() *tileSize);
        ((MoveToPoint)e.getBehaviour()).setTarget(p);
    }

    public void update(){
       if(((MoveToPoint)e.getBehaviour()).getPoint() == null){
        step();
           System.out.println("Stepping");
       }
    }

    public static void updateAll(){
        for(CompletePath p : paths){
            if(p.remove){
                paths.remove(p);
            }else
            p.update();
        }
    }
}
