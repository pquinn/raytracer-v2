package edu.pquinn.raytracer.surface;

import edu.pquinn.raytracer.Ray;
import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public class Sphere extends Surface{
	protected int centerVertex;
	Vector center, direction;
	
	public Sphere(int centerVertex, float[] aM, float[] dM, float[] sM, int sE){
		super(aM, dM, sM, sE);
		this.centerVertex = centerVertex;
	}
	
	//checks if the ray intersects the sphere
	public double intersectRay(Ray r, double t0, double t1, HitResult u) {
		double a = r.getD().dotProduct(r.getD());
		Vector twod = r.getD().multiplyByScalar(2);
		Vector qminusc = r.getS().subtractVectors(this.center);
		double b = twod.dotProduct(qminusc);
		double rsq = direction.dotProduct(direction);
		double c = qminusc.dotProduct(qminusc) - rsq;
		double discrim = (b * b) - (4 * a * c);
		double tclose = Double.POSITIVE_INFINITY;
		double tfar = Double.POSITIVE_INFINITY;
		if(discrim >= 0){
			tclose = (-b / (2 * a)) - (Math.sqrt(discrim)/(2 * a));
		}
		if(tclose >= t0 && tclose < t1){
			Vector intPoint = r.computeIntersectionPoint(tclose);
			Vector surfaceNorm = intPoint.subtractVectors(this.center);
			u.setIntersectionPoint(intPoint);
			u.setSurfaceNormal(surfaceNorm);
			u.setTag("sphere");
			return tclose;
		}
		tfar = (-b / (2 * a)) + (Math.sqrt(discrim)/(2 * a));
		if(tfar >= t0 && tfar < t1){
			Vector intPoint = r.computeIntersectionPoint(tfar);
			Vector surfaceNorm = intPoint.subtractVectors(this.center);
			u.setIntersectionPoint(intPoint);
			u.setSurfaceNormal(surfaceNorm);
			u.setSurfaceNormal(surfaceNorm);
			u.setTag("sphere");
			return tfar;
		}
		return Double.POSITIVE_INFINITY;
	}
	
	//gets the vertex index of this sphere
	public int getVertexIndex() {
		return this.centerVertex;
	}

	//returns the center
	public Vector getCenter() {
		return this.center;
	}

	//returns the direction
	public Vector getDirection() {
		return this.direction;
	}

	//sets the center of this sphere
	public void setCenter(Vector center) {
		this.center = center;
	}
	
	//sets the direction of this sphere
	public void setDirection(Vector dir) {
		this.direction = dir;
	}
	
	//returns what kind of shape this is
	public String getName(){
		return "sphere";
	}

	//computes the surface normal of this sphere
	public Vector getSurfaceNormal(Vector p, HitResult hr) {
		return p.subtractVectors(this.center).normalize();
	}
	
}
