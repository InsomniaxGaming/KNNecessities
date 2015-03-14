package com.kingsnest.knnecessities.datatypes;

public class Home{
	private Location location;
	private String ownerUUID;
	
	public Home(Location loc, String ownerUUID)
	{
		setLocation(loc);
		setOwnerUUID(ownerUUID);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(String ownerUUID) {
		this.ownerUUID = ownerUUID;
	}
}
