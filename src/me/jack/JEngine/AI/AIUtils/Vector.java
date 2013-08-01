package me.jack.JEngine.AI.AIUtils;

public class Vector {

	private float x;
	private float y;
	public Vector(float x,float y){
		this.x= x;
		this.y = y;
	}
	public Vector(int x,int y){
		this.x = x;
		this.y =y;
	}
	
	public float getX(){
		return this.x;
	}
	public float getY(){
		return this.y;
	}
	
	public int getRoundedX(){
		return (int)this.x;
	}
	
	public int getRoundedY(){
		return (int)this.y;
	}
}
