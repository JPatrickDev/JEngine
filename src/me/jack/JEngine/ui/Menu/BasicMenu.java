package me.jack.JEngine.ui.Menu;


import java.util.ArrayList;

import me.jack.JEngine.JEngine;

import me.jack.JEngine.ui.Button.ArrowKeyButton;
import me.jack.JEngine.ui.Button.Button;
import me.jack.JEngine.ui.Button.ButtonListener;
import me.jack.JEngine.ui.Button.IconButton;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class BasicMenu extends Menu{

	public ArrayList<Button> buttons = new ArrayList<Button>();
	
	int selected = 0;
	
	ButtonListener listener = null;
	String title = "";
        int tx = 0;
        int ty = 0;
        Color titleColor = Color.white;
    @Override
    public void render(Graphics g){
        g.setFont(JEngine.font);
        for(Button b : buttons){
            b.render(g);
        }
        g.setColor(titleColor);
		g.drawString(title, tx, ty);
		g.setColor(Color.white);
		
	}
	
	int buttonY = 0;
	int buttonX = 0;
    public int padding = 150;
	/**
	 * 
	 * @param b - The text of the Button
	 * @param arg0 - The GameContainer of the game
	 */
	public void addArrowButton(String b, GameContainer arg0){
        if(buttonY == 0){
            ty = (arg0
                    .getHeight() / 4) - 50;
            buttonY = ty + padding;

            buttonX = arg0.getWidth() / 2;
        }
        else{
            buttonY += 80;
        }
        Button button = new ArrowKeyButton(b, false, buttonX, buttonY, this);
        buttons.add(button);
    }

    public void addIconButton(String b,String res, GameContainer arg0){
        if(buttonY == 0){
            ty = (arg0
                    .getHeight() / 4) - 50;
            buttonY = ty + padding;
           int width = (JEngine.SCREEN_WIDTH / 4) + 80;
           int height = JEngine.SCREEN_HEIGHT / 10;
            buttonX = (arg0.getWidth() / 2) - width/2;
        }
        else{
            buttonY += 80;
        }
        System.out.println(b);
        IconButton button = new IconButton(b, false, buttonX, buttonY, this,res);
        buttons.add(button);
    }

    public void addTitle(String title,GameContainer c,Color tColor){
		this.title = title;
		this.tx = (c.getWidth() / 2) - (JEngine.font.getWidth(title) / 2);
		this.titleColor = tColor;
	}
	
	/**
	 * Adds a button at a specific x and y
	 * @param b - The Button text
	 * @param buttonX - The x for the button
	 * @param buttonY - the y for the button
	 */
	public void addButton(String b,int buttonX, int buttonY){
		Button button = new ArrowKeyButton(b, true, buttonX, buttonY, this);
		buttons.add(button);
	}
    public void addIconButton(String text,String icon, int bx,int by){
        IconButton button = new IconButton(text, true, buttonX, buttonY, this,icon);
        buttons.add(button);
    }
	
	int wait = 0;
	boolean shouldwait = false;
    @Override
	public void update(GameContainer arg0){
		//System.out.println(wait);
		if(shouldwait){
		wait++;
		if(wait != 10){
			return;
		}
		wait = 0;
		shouldwait = false;
		}
		if(listener == null){
			throw new NullPointerException("Listener cannot be null");
		}
		for(Button b : buttons){
			b.update(arg0);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			if(selected == 0){
				listener.onButtonDeselect(buttons.get(selected));
				selected = buttons.size() - 1;
				listener.onButtonSelect(buttons.get(selected));
			}
			else{
				listener.onButtonDeselect(buttons.get(selected));
				selected--;
				listener.onButtonSelect(buttons.get(selected));
			}
			shouldwait = true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if(selected == buttons.size() - 1){
				listener.onButtonDeselect(buttons.get(selected));
				selected = 0;
				listener.onButtonSelect(buttons.get(selected));
			}
			else{
				listener.onButtonDeselect(buttons.get(selected));
				selected++;
				listener.onButtonSelect(buttons.get(selected));
			}
			shouldwait = true;
		}
		for(Button b: buttons){
            if(b instanceof ArrowKeyButton){
                ArrowKeyButton bt = (ArrowKeyButton) b;
                bt.selected = false;
            }
		}
        if(buttons.get(selected) instanceof ArrowKeyButton){
            ArrowKeyButton button= (ArrowKeyButton) buttons.get(selected);
            button.selected = true;
        }

	}
	
	public void setListener(ButtonListener l){
		System.out.println("Listener set to : " + l == null);
		this.listener = l;
	}
    @Override
	public ButtonListener getListener(){
		return this.listener;
	}
}