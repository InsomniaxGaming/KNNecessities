package com.kingsnest.knnecessities.datatypes;

import net.minecraft.world.World;

public class Location {
	private String world; // World
	private int dimension; // Dimension
	private double x,y,z; // Position
	private float pitch,yaw; // Rotation
		
	public Location (String world, int dimension, double x, double y, double z, float pitch, float yaw)
	{
		this.setWorld(world);
		this.setDimension(dimension);
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public String getWorld() {
		return world;
	}
	
	public void setWorld(String world) {
		this.world = world;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
}
