package edu.pquinn.raytracer.surface;

import edu.pquinn.raytracer.Ray;
import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public class Triangle extends Surface{
	protected int[] indeces = new int[3];
	
	public Triangle(int[] indeces, float[] aM, float[] dM, float[] sM, int sE){
		super(aM, dM, sM, sE);
		this.indeces = indeces;
	}
	
	public double intersectRay(Ray r, double t0, double t1, HitResult u) {
		return Double.POSITIVE_INFINITY;
	}

	public int getVertexIndex() {
		return 0;
	}

	public String getName(){ return null; }
	public Vector getDirection() { return null; }
	public void setDirection(Vector dir) { } 
	public Vector getCenter() { return null; }
	public void setCenter(Vector dir) { }

	@Override
	public Vector getSurfaceNormal(Vector p, HitResult hr) {
		return null;
	} 
}
