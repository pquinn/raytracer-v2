package edu.pquinn.raytracer.light;

import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public class PointLight extends Light{
	protected int vertex;
	float intensity;
	Vector center;
	Vector lhat = new Vector(0,0,0);
	
	public PointLight(int vertex, float intensity){
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

	//gets the vertex index of this light
	public int getVertex() {
		return this.vertex;
	}
	
	//returns the direction of this light
	public Vector getDirection() {
		return null;
	}

	//calculates the lhat of this light
	public void calculatelhat(HitResult hr) {
		lhat.setX(this.center.getX() - hr.getIntersectionPoint().getX());
		lhat.setY(this.center.getY() - hr.getIntersectionPoint().getY());
		lhat.setZ(this.center.getZ() - hr.getIntersectionPoint().getZ());
	}

	//returns the lhat of this light
	public Vector getlhat() {
		return this.lhat;
	}

	//sets the direction of this light
	public void setDirection(Vector dir) {
		
	}

	//returns the max t of this light
	public double getTMax() {
		return 1;
	}
}
