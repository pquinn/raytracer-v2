package edu.pquinn.raytracer;

import edu.pquinn.raytracer.util.Vector;

public class Camera {
	protected int vertex;
	protected Vector xAxis = new Vector(1, 0, 0);
	protected Vector yAxis = new Vector(0, 1, 0);
	protected Vector zAxis = new Vector(0, 0, 1);
	protected Vector origin = new Vector(0, 0, 0);
	protected double[] d;
	
	public Camera(int vertex){
		this.vertex = vertex;
	}
	
	//returns the x axis of the camera
	public Vector getXAxis(){
		return this.xAxis;
	}
	
	//returns the y axis of the camera
	public Vector getYAxis(){
		return this.yAxis;
	}
	
	//returns the z axis of the camera
	public Vector getZAxis(){
		return this.zAxis;
	}
	
	//returns the vertex index of the camera
	public int getVertex(){
		return this.vertex;
	}

	public void setXAxis(Vector newX){
		this.xAxis = newX;
	}
	
	public void setYAxis(Vector newY){
		this.yAxis = newY;
	}
	
	public void setZAxis(Vector newZ){
		this.zAxis = newZ;
	}
	
	public void setOrigin(Vector newOrigin){
		this.origin = newOrigin;
	}
	
	public Vector getOrigin(){
		return this.origin;
	}
	
	public void setDirection(double[] newDir){
		this.d = newDir;
	}
}
