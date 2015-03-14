package com.kingsnest.knnecessities.datatypes;

import java.util.UUID;

public class Home{
	private Location location;
	private UUID ownerUUID;
	private String homeName;
	
	public Home(Location loc, UUID ownerUUID)
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

	public UUID getOwnerUUID() {
		return ownerUUID;
	}

	public void setOwnerUUID(UUID ownerUUID) {
		this.ownerUUID = ownerUUID;
	}
}
