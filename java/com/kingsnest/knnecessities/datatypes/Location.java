package com.kingsnest.knnecessities.datatypes;

import net.minecraft.world.World;

public class Location {
	private String world; // World
	private double x,y,z; // Position
	private double a,b,c; // Rotation
	
	
	public String getWorld() {
		return world;
	}
	
	public void setWorld(String world) {
		this.world = world;
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
	
}
