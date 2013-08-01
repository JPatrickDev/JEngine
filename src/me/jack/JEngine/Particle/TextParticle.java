package me.jack.JEngine.Particle;

import java.util.Random;

import me.jack.JEngine.ui.Numbers;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class TextParticle extends Particle {
	private String msg;
	private int time = 0;
	public double xa, ya, za;
	public double xx, yy, zz;

	Image text = null;
	public TextParticle(String msg, int x, int y) {
		super(x,y);
		this.msg = msg;
		this.text=  Numbers.getNumber(Integer.parseInt(msg));
		xx = x;
		yy = y;
		zz = 2;
		Random random = new Random();
		xa = random.nextGaussian() * 0.5;
		ya = random.nextGaussian() * 0.9;
		za = random.nextFloat() * 0.7 + 5;
	}

	@Override
	public void update(ParticleSystem map) {
		time++;
		if (time > 70) {
			map.particles.remove(this);
		}
		xx += xa;
		yy += ya;
		zz += za;
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.9;
			ya *= 0.6;
		}
		za -= 0.15;
		x = (int) xx;
		y = (int) yy;
	}

	public void render(Graphics g,int xoffset,int yoffset) {
		g.drawImage(text,(x - msg.length()) - xoffset, y - yoffset);
	}
}