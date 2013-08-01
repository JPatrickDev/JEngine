package me.jack.JEngine.Particle;

import me.jack.JEngine.Util.ImageUtil;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class FireParticle extends Particle {
	private int time = 0;
	public double xa, ya, za;
	public double xx, yy, zz;

	static Image particle = null;

	public FireParticle(int x, int y) {
		super(x, y);
		if (particle == null)
			particle = ImageUtil.loadImage("/res/fireParticle.png");
		xx = x;
		yy = y;
		zz = 2;
		xa = random.nextGaussian() * 0.9;
		ya = random.nextGaussian() * 1.2;
		za = random.nextFloat() * 5.9 + 2;

	}

	@Override
	public void update(ParticleSystem map) {
		time++;
		if (time > 50) {
			map.particles.remove(this);
		}
		xx += xa;
		yy += ya;
		zz += za;
		if (zz < 0) {
			zz = 0;
			za *= -0.5;
			xa *= 0.1;
			ya *= 0.6;
		}
		za -= 0.15;
		x = (int) xx;
		y = (int) yy;
	}

	public void render(Graphics g, int xoffset, int yoffset) {
		g.drawImage(particle, x - xoffset, y - yoffset);
	}
}
