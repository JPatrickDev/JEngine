package me.jack.JEngine.test;

import me.jack.JEngine.JEngine;
import me.jack.JEngine.Level.Camera;
import me.jack.JEngine.Level.Collision;
import me.jack.JEngine.Level.Level;
import me.jack.JEngine.Level.TiledCamera;
import me.jack.JEngine.Particle.BloodSplatParticle;
import me.jack.JEngine.Particle.FireParticle;
import me.jack.JEngine.Particle.ParticleSystem;
import me.jack.JEngine.Util.Utils;
import me.jack.JEngine.ui.Button.Button;
import me.jack.JEngine.ui.Button.ButtonListener;
import me.jack.JEngine.ui.Menu.BasicMenu;
import me.jack.JEngine.ui.Numbers;
import me.jack.JEngine.ui.Popup;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class MainMenuState extends BasicGameState implements ButtonListener{
    BasicMenu menu = new BasicMenu();
	StateBasedGame game;
	//TextBox box = new TextBox(90,90);
	ParticleSystem particle = new ParticleSystem();
	
	TiledMap map = null;
	
	ArrayList<Popup> popups = new ArrayList<Popup>();
	TiledCamera camera = null;


    Image image;
    Collision collision = new Collision();
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Numbers.loadNumbers();
		JEngine.loadFont();

        menu.setListener(this);
          menu.addTitle("TEST", arg0, Color.white);
        menu.addIconButton("New Game","/res/icon.png",arg0);
      //  menu.padding = 200;
      //  menu.addButton("New Game",arg0);
     //   menu.addButton("New Game",arg0);  menu.addButton("New Game",arg0);
      //  menu.addButton("New Game",arg0);

    map = new TiledMap("/res/map.tmx");

        camera = new TiledCamera(arg0,map);

    collision.load(map);

	}

    int pX = 300;
    int pY = 200;

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		game = arg1;
		
        //determine cameraX and cameraY
//after calculating the positions of all entities


//in the render()-method
        camera.drawMap();
        camera.translateGraphics();

        g.fillRect(pX,pY,32,32);

	}


	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
        menu.update(arg0);
		camera.centerOn(pX,pY);
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(canMove(pX,pY-5))
			pY-=5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if(canMove(pX - 5,pY))
			pX-=5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if(canMove(pX,pY+5))
			pY+=5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if(canMove(pX + 5,pY))
			pX+=5;
		}
		particle.update();
		if(Mouse.isButtonDown(0)){
            for(int i = 0;i!= 50;i++){
            if(new Random().nextBoolean())
                particle.addParticle(new FireParticle(arg0.getInput().getMouseX(),arg0.getInput().getMouseY()));
                else
                particle.addParticle(new BloodSplatParticle(arg0.getInput().getMouseX(),arg0.getInput().getMouseY()));
            }
        }
		for(Popup p: this.popups){
			p.update();
		}
	}


    int mapTileSize_ = 32;
    private void drawLayers(int x, int y, int w, int h){
        int tileOffsetX = (-1*x%mapTileSize_);
        int tileOffsetY = (-1*y%mapTileSize_);
        int tileIndexX  = x/mapTileSize_;
        int tileIndexY  = y/mapTileSize_;

        for(int i  = 0;i!= map.getLayerCount();i++){
            map.render(x + tileOffsetX,y+ tileOffsetY, tileIndexX, tileIndexY,
                    (w - tileOffsetX)/mapTileSize_ + 1,
                    (h - tileOffsetY)/mapTileSize_ + 1,
                    i, false);
        }
    }

    public boolean canMove(int newX,int newY){
     Rectangle player = new Rectangle(newX,newY,32,32);
        if(!collision.check(player)){
            return false;
        }
        return true;
    }
	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void onButtonSelect(Button button) {

	}

	@Override
	public void onButtonDeselect(Button button) {

	}

	@Override
        public void onButtonActivate(Button button) {
            if(button.text.equalsIgnoreCase("New Game")){
            System.out.println("NG");
            }
            if(button.text.equalsIgnoreCase("click")){
            System.out.println("Click");
        }
	}

}
