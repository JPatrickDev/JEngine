package me.jack.JEngine.Level;

public class Camera {

	public int x;
	public int y;

	int width;
	int height;

	int tileSize;

	public Camera(int x, int y, int width, int height, int tileSize) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
	}

	public void addX(int x) {
		this.x += x;
	}

	public void addY(int y) {
		this.y += y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void centerOnPlayer(int px, int py, int mWidth, int mHeight) {
		x = px - (width / 2);
	if (x < 0)
		x = 0;
		if (x >= (mWidth * this.tileSize) - width) {
			x = (mWidth * this.tileSize) - width;
		}
		y = py - (height / 2);
	if (y < 0)
		y = 0;
		if (y >= ((mHeight) * this.tileSize) - height) {
			y = ((mHeight)*this.tileSize) - height;
		}
		y = 0;
	}
}
