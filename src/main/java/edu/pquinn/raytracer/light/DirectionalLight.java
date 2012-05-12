package edu.pquinn.raytracer.light;

import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public class DirectionalLight extends Light{
	protected int vertex;
	float intensity;
	Vector center;
	Vector direction;
	Vector lhat = new Vector(0,0,0);
	
	DirectionalLight(int vertex, float intensity){
		this.vertex = vertex;
		this.intensity = intensity;
	}
	
	//returns the intensity of this light
	public float getIntensity() {
		return this.intensity;
	}
	
	//changes the center of this light
	public void setCenter(Vector center){
		this.center = center;
	}
	
	//returns the center of this light
	public Vector getCenter(){
		return this.center;
	}

	//returns the vertex index of this light
	public int getVertex() {
		return this.vertex;
	}

	//returns the direction of this light
	public Vector getDirection() {
		return this.direction;
	}

	//changes the direction of this light
	public void setDirection(Vector dir) {
		this.direction = dir;
	}

	//calculates the lhat of this light
	public void calculatelhat(HitResult hr) {
		lhat.setX(direction.getX() * -1);
		lhat.setY(direction.getY() * -1);
		lhat.setZ(direction.getZ() * -1);
	}

	//returns the lhat of this light
	public Vector getlhat() {
		return this.lhat;
	}
	
	//returns the max t of this light
	public double getTMax(){
		return Double.POSITIVE_INFINITY;
	}
}
