package me.jack.JEngine.Particle;

import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Graphics;

public class ParticleSystem {

	CopyOnWriteArrayList<Particle> particles = new CopyOnWriteArrayList<Particle>();
	
	
	public void render(Graphics g,int offsetX,int offsetY){
		for(Particle p : particles){
			p.render(g, offsetX, offsetY);
		}
	}
	public void update(){
		for(Particle p : particles){
			p.update(this);
		}
	}
	
	public void addParticle(Particle p){
		particles.add(p);
	}

    public void removeParticle(Particle p){
        particles.remove(p);
    }
}
