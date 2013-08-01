package me.jack.JEngine.Particle;

import java.util.Random;

import org.newdawn.slick.Graphics;

public abstract class Particle {
	public Random random = new Random();
	public int x;
	public int y;
	public Particle(int x, int y){
		this.x = x;
		this.y =y;
	}
	
	public abstract void render(Graphics g,int offsetX,int offsetY);
	public abstract void update(ParticleSystem system);
}
