package me.jack.JEngine.AIUtils;

import java.awt.Point;





public class MathsUtils {

	
	public static Vector getVelocityToTarget(int tX,int tY, int cX,int cY){
		float xSpeed = (tX- cX);
		float ySpeed = (tY - cX);
		float factor = (float) (9 / Math
				.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
		xSpeed = xSpeed * factor;
		ySpeed = ySpeed * factor;
		return new Vector(xSpeed,ySpeed);
	}
	public static Vector getVelocityToTarget(int tX,int tY, int cX,int cY,int speed){
		float xSpeed = (tX- cX);
		float ySpeed = (tY - cX);
		float factor = (float) (speed / Math
				.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
		xSpeed = xSpeed * factor;
		ySpeed = ySpeed * factor;
		return new Vector(xSpeed,ySpeed);
	}
	
	public double getDistanceToTarget(Point first,Point second){
	return	Math.sqrt((first.x-second.x)*(first.x*second.x) + (first.y-second.y)*(first.y-second.y));
	}
}
