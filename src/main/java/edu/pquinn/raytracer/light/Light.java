package edu.pquinn.raytracer.light;

import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public abstract class Light {
	//returns the intensity of the light
	public abstract float getIntensity();
	
	//changes the center of the light
	public abstract void setCenter(Vector center);

	//returns the center of the light
	public abstract Vector getCenter();
	
	//returns the vertex
	public abstract int getVertex();
	
	//changes the direction of the light
	public abstract void setDirection(Vector dir);
	
	//returns the direction of the light
	public abstract Vector getDirection();
	
	//returns the lhat of the light
	public abstract Vector getlhat();
	
	//calculates the lhat of the light
	public abstract void calculatelhat(HitResult hr);
	
	//returns the max T of the light
	public abstract double getTMax();
}
