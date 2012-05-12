package edu.pquinn.raytracer.util;

public class Vector {
	double x, y, z;
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//subtracts two vectors
	public Vector subtractVectors(Vector v){
		Vector result = new Vector(0,0,0);
		result.setX(this.getX() - v.getX());
		result.setY(this.getY() - v.getY());
		result.setZ(this.getZ() - v.getZ());
		return result;
	}
	
	//normalizes a vector
	public Vector normalize(){
		Vector result = new Vector(0,0,0);
		double magnitude = this.magnitude();
		result.setX(this.getX() / magnitude);
		result.setY(this.getY() / magnitude);
		result.setZ(this.getZ() / magnitude);
		return result;
	}
	
	//computes the cross product of this and p2
	public Vector cross(Vector p2){
		Vector result = new Vector(0,0,0);
		result.setX(this.getY() * p2.getZ() - this.getZ() * p2.getY());
		result.setY(this.getZ() * p2.getX() - this.getX() * p2.getZ());
		result.setZ(this.getX() * p2.getY() - this.getY() * p2.getX());
		return result;
	}
	
	//negates a vector
	public Vector negateVector(){
		Vector result = new Vector(0,0,0);
		result.setX(this.getX() * -1);
		result.setY(this.getY() * -1);
		result.setZ(this.getZ() * -1);
		return result;
	}
	
	//computes the dot product of this and a
	public double dotProduct(Vector a){
		return (this.getX() * a.getX()) +
			   (this.getY() * a.getY()) + 
			   (this.getZ() * a.getZ());
	}
	
	//computes the magnitude of this
	public double magnitude(){
		return Math.sqrt((this.getX() * this.getX()) + (this.getY() * this.getY()) + (this.getZ() * this.getZ())); 
	}
	
	//adds this and v
	public Vector addVector(Vector v){
		Vector result = new Vector(0,0,0);
		result.setX(this.getX() + v.getX());
		result.setY(this.getY() + v.getY());
		result.setZ(this.getZ() + v.getZ());
		return result;
	}
	
	//multiplies this by a scalar with an int input
	public Vector multiplyByScalar(int n){
		Vector result = new Vector(0,0,0);
		result.setX(this.getX() * n);
		result.setY(this.getY() * n);
		result.setZ(this.getZ() * n);
		return result;
	}
	
	//multiplies this by a scalar with a double
	public Vector multiplyByScalar(double n){
		Vector result = new Vector(0,0,0);
		result.setX(this.getX() * n);
		result.setY(this.getY() * n);
		result.setZ(this.getZ() * n);
		return result;
	}
	
	//returns the X value
	public double getX(){
		return this.x;
	}
	
	//returns the Y value
	public double getY(){
		return this.y;
	}
	
	//returns the Z value
	public double getZ(){
		return this.z;
	}
	
	//changes the X value
	public void setX(double x){
		this.x = x;
	}
	
	//changes the Y value
	public void setY(double y){
		this.y = y;
	}
	
	//changes the Z value
	public void setZ(double z){
		this.z = z;
	}

}
