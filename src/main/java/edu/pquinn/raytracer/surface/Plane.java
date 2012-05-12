package edu.pquinn.raytracer.surface;
import java.util.Arrays;

import edu.pquinn.raytracer.Ray;
import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;


public class Plane extends Surface{
	protected int centerVertex;
	Vector center, direction;
	
	public Plane(int centerVertex, float[] aM, float[] dM, float[] sM, int sE){
		super(aM, dM, sM, sE);
		this.centerVertex = centerVertex;
	}
	
	public double intersectRay(Ray r, double t0, double t1, HitResult u) {
		double ddotn = r.getD().dotProduct(this.direction);
		if(Math.abs(ddotn) >= .000001){
			double t = (this.center.dotProduct(this.direction) - r.getS().dotProduct(this.direction)) / ddotn;
			if(t >= t0 && t <= t1){
				Vector intPoint = r.computeIntersectionPoint(t);
				u.setIntersectionPoint(intPoint);
				u.setSurfaceNormal(this.direction);
				u.setTag("plane");
				return t;
			} else return Double.POSITIVE_INFINITY;
		}
		else
			return Double.POSITIVE_INFINITY;
	}

	public int getVertexIndex() {
		return this.centerVertex;
	}

	public Vector getCenter() {
		return this.center;
	}

	public Vector getDirection() {
		return this.direction;
	}

	public void setCenter(Vector center) {
		this.center = center;
	}

	public void setDirection(Vector dir) {
		this.direction = dir.normalize();
	}
	
	public String getName(){
		return "plane";
	}

	public Vector getSurfaceNormal(Vector p, HitResult hr) {
		return this.direction;
	}
}
