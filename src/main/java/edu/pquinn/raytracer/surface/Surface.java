package edu.pquinn.raytracer.surface;

import edu.pquinn.raytracer.Ray;
import edu.pquinn.raytracer.util.HitResult;
import edu.pquinn.raytracer.util.Vector;

public abstract class Surface {
	float[] ambientMaterial = new float[3], diffuseMaterial = new float[3],
			specularMaterial = new float[3];
	int specularExponent;

	Surface(float[] ambientMaterial, float[] diffuseMaterial,
			float[] specularMaterial, int specularExponent) {
		for (int i = 0; i < 3; i++) {
			this.ambientMaterial[i] = ambientMaterial[i];
			this.diffuseMaterial[i] = diffuseMaterial[i];
			this.specularMaterial[i] = specularMaterial[i];
		}
		this.specularExponent = specularExponent;
	}

	// checks if the ray intersects with the surface
	public abstract double intersectRay(Ray r, double t0, double t1, HitResult u);

	// returns the vertex index
	public abstract int getVertexIndex();

	// changes the center of the surface
	public abstract void setCenter(Vector center);

	// returns the center of the surface
	public abstract Vector getCenter();

	// changes the direction of the surface
	public abstract void setDirection(Vector dir);

	// returns the direction of the surface
	public abstract Vector getDirection();

	// returns the name of the surface
	public abstract String getName();

	// returns the surface normal of the surface
	public abstract Vector getSurfaceNormal(Vector p, HitResult hr);

	// returns the specular material of this surface
	public float[] getSpecularMaterial() {
		return this.specularMaterial;
	}

	public float[] getAmbientMaterial() {
		return ambientMaterial;
	}

	public void setAmbientMaterial(float[] ambientMaterial) {
		this.ambientMaterial = ambientMaterial;
	}

	public float[] getDiffuseMaterial() {
		return diffuseMaterial;
	}

	public void setDiffuseMaterial(float[] diffuseMaterial) {
		this.diffuseMaterial = diffuseMaterial;
	}

	public int getSpecularExponent() {
		return specularExponent;
	}

	public void setSpecularExponent(int specularExponent) {
		this.specularExponent = specularExponent;
	}

	public void setSpecularMaterial(float[] specularMaterial) {
		this.specularMaterial = specularMaterial;
	}

}
