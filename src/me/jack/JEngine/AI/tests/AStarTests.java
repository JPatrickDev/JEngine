package me.jack.JEngine.AI.tests;


import me.jack.JEngine.AI.Behaviour.Movement.CompletePath;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import me.jack.JEngine.AI.ASTAR.*;

/**
 * Author: Jack
 * Date: 29/07/13
 */
public class AStarTests extends BasicGame {


    public static ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

    public static BasicEntity player = null;

    public TestLevel level = new TestLevel();

    public int[] map = new int[(800 / 32) * (600 / 32)];
    private List<Node> path;

    public AStarTests(String title) {
        super(title);

    }

    int tX;
    int tY;
    Map<Node> myMap;
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        player = new BasicEntity((1*32), (1*32), level);

        int w = 0;
        int h = 0;
        try {
            Scanner scanner = new Scanner(new File("hitbox.txt"));
            int y = 0;
            ArrayList<Point> points = new ArrayList<Point>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int x = 0;
                for(char c: line.toCharArray()){
                    int i = Integer.parseInt(String.valueOf(c));
                    System.out.println(i);
                    if(i == 1){
                        System.out.println("Impassable at: " + x + ":" +y);
                        points.add(new Point(x,y));

                    }
                    x++;
                }
                w = x;
                y++;
            }
            h =y;
            myMap = new Map<Node>(w,h, new BasicNodeFactory());
            for(Point p : points){
                myMap.setWalkable(p.x,p.y,false);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tX = (int) getRandomInteger(0, 800);
        tY = (int) getRandomInteger(0, 600);

        for(int i = 0;i!= map.length;i++){
            map[i] = 0;
        }



        path =  myMap.findPath(1, 1, 1,6);
        new CompletePath(new  ArrayList<Node>(path),player,32);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        player.update(i);
        CompletePath.updateAll();
    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {

    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        //  entities.add(new AttackingEntity(x, y, level));
    }


    public double getRandomInteger(int min, int max) {
        return min + Math.floor(Math.random() * (max + 1 - min));
    }


    int w = (800/32);
    int h = (600/32);
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {


        myMap.drawMap(graphics);
        graphics.setColor(Color.white);


        for (Rectangle r : hitboxes) {
            graphics.fill(r);
        }

        graphics.fillRect(tX, tY, 32, 32);

        graphics.setColor(Color.red);
        for (Node n : path) {
            graphics.setColor(Color.orange);
            graphics.fillRect(n.getxPosition()*32, n.getyPosition()*32, 32, 32);

        }
        graphics.setColor(Color.white);


        player.render(graphics);
    }

}
