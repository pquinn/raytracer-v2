package edu.pquinn.raytracer;

import edu.pquinn.raytracer.util.Vector;

public class Ray {
	Vector s;
	Vector d;

	// p(t) = s + td
	public Ray(Vector s, Vector d) {
		// start
		this.s = s;
		// direction
		this.d = d;
	}

	public Vector getS() {
		return s;
	}

	public void setS(Vector s) {
		this.s = s;
	}

	public Vector getD() {
		return d;
	}

	public void setD(Vector d) {
		this.d = d;
	}

	// computes the intersection point based on a given t
	public Vector computeIntersectionPoint(double t) {
		Vector td = new Vector(t * this.d.getX(), t * this.d.getY(), t
				* this.d.getZ());
		Vector qplustd = new Vector(this.s.getX() + td.getX(), this.s.getY()
				+ td.getY(), this.s.getZ() + td.getZ());
		return qplustd;
	}
}
