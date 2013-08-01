package me.jack.JEngine.Particle;

import java.util.Map;
import java.util.Random;

import me.jack.JEngine.Util.ImageUtil;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BloodSplatParticle extends Particle{
	private int time = 0;
	public double xa, ya, za;
	public double xx, yy, zz;

	static Image particle = null;
	public BloodSplatParticle(int x, int y) {
		super(x,y);
		if(particle == null)
		particle = ImageUtil.loadImage("/res/bloodSplat.png");
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
		if (time > 30) {
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
g.drawImage(particle, x - xoffset, y - yoffset);
	}
}
