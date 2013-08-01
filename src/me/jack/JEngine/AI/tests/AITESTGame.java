package me.jack.JEngine.AI.tests;

import me.jack.JEngine.AI.Entity.Entity;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Author: Jack
 * Date: 19/07/13
 */
public class AITESTGame extends BasicGame {


    private CopyOnWriteArrayList<Entity> entities = new CopyOnWriteArrayList<Entity>();

    public static ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

    public static BasicEntity player = null;

    public TestLevel level = new TestLevel();

    public AITESTGame(String title) {
        super(title);

    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        player = new BasicEntity(400, 300, level);
        try {
            Scanner scanner = new Scanner(new File("hitbox.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(":");
                System.out.println("Loaded rect: " + line);
                hitboxes.add(new Rectangle(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        for (Entity e : entities) {
            e.update(i);
            if (e.isDead())
                entities.remove(e);
        }
        player.update(i);

    }

    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy) {
        if (Mouse.isButtonDown(0))
            //  for(int i = 0;i!= 50;i++)
            entities.add(new AttackingEntity(newx, newy, level));
    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        entities.add(new AttackingEntity(x, y, level));
    }


    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (Entity e : entities) {
            e.render(graphics);
        }
        player.render(graphics);

        for(Rectangle r : hitboxes){
            graphics.fill(r);
        }
    }
}
