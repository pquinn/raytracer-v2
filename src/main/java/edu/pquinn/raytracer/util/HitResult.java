package edu.pquinn.raytracer.util;
import java.util.Arrays;



public class HitResult {
	Vector surfaceNormal, intersectionPoint; 
	double[] barycentricCoords;
	String tag;
	
	public HitResult(Vector surfaceNormal, Vector intersectionPoint, double[] barycentricCoords, String tag){
		this.surfaceNormal = surfaceNormal;
		this.intersectionPoint = intersectionPoint;
		this.barycentricCoords = barycentricCoords;
		this.tag = tag;
	}
	
	public Vector getIntersectionPoint() {
		return this.intersectionPoint;
	}
	
	public void setIntersectionPoint(Vector point){
		this.intersectionPoint = point;
	}
	
	public Vector getSurfaceNormal() {
		return this.surfaceNormal;
	}
	
	public void setSurfaceNormal(Vector d){
		this.surfaceNormal = d;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	public String getTag(){
		return this.tag;
	}
	
	public void resetHitResult(){
		this.surfaceNormal = new Vector(0,0,0);
		this.intersectionPoint = new Vector(0,0,0);
		this.barycentricCoords = new double[3];
		this.tag = null;
	}
}
