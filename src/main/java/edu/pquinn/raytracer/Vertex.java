package edu.pquinn.raytracer;
import java.util.StringTokenizer;

import edu.pquinn.raytracer.util.Vector;


public class Vertex {
	double x, y, z, dx, dy, dz;
	
	Vertex(StringTokenizer line){
		this.x = Double.parseDouble(line.nextToken());
		this.y = Double.parseDouble(line.nextToken());
		this.z = Double.parseDouble(line.nextToken());
		this.dx = Double.parseDouble(line.nextToken());
		this.dy = Double.parseDouble(line.nextToken());
		this.dz = Double.parseDouble(line.nextToken());
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
		return this.z;
	}
	
	public Vector getPt(){
		return new Vector(this.x, this.y, this.z);
	}
	
	public Vector getD(){
		return new Vector(this.dx, this.dy, this.dz);
	}
	
}
