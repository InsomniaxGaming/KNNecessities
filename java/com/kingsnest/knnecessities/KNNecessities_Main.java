package com.kingsnest.knnecessities;

// Java imports
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;





// KN imports
import com.kingsnest.knnecessities.datatypes.Home;
import com.kingsnest.knnecessities.datatypes.Location;
import com.kingsnest.knnecessities.commands.CMD_Home;
import com.kingsnest.knnecessities.commands.CMD_SetHome;





// Minecraft / Forge imports
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.server.FMLServerHandler;

@Mod(modid = KNNecessities_Main.MODID, version = KNNecessities_Main.VERSION, name = KNNecessities_Main.NAME )
public class KNNecessities_Main {
	
	// Static stoof.
	public static final String MODID = "knnecessities";
	public static final String VERSION = "0.0.1 Alpha";
    public static final String NAME = "King's Nest Necessary Commands";
    

    @Instance(MODID)
    public KNNecessities_Main instance;
    
    @SidedProxy(clientSide="com.kingsnest.knnecessities.KNNClientProxy", serverSide="com.kingsnest.knnecessities.KNNCommonProxy")
    public static KNNClientProxy proxy;
    
    // List of Homes
    private List<Home> homes;
    
    // Space point
    private Location spawn;
    
    // Config object
    public Configuration config = null;
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());

        // loading the configuration from its file
        config.load();
	}

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// Just to get our name in the log. :P
        System.out.println(NAME + " v" + VERSION + " loading.");
        
        homes = new ArrayList<Home>();
        
    }
    
    @EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		
	}
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
    	event.registerServerCommand(new CMD_Home(this)); // Add /home handler
    	event.registerServerCommand(new CMD_SetHome(this)); // Add /sethome handler
    	return;
    }
    
    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event)
    {
    	for(Home home:getHomes())
    	{
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.world", home.getLocation().getWorld());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.dimension", home.getLocation().getDimension());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.X", home.getLocation().getX());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.Y", home.getLocation().getY());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.Z", home.getLocation().getZ());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.Pitch", home.getLocation().getPitch());
    		config.get(Configuration.CATEGORY_GENERAL, home.getOwnerUUID().toString() +".home.Yaw", home.getLocation().getYaw());
    	}
    	
    	config.save();
    	return;
    }
    
    
    public boolean sendPlayerToLocation(EntityPlayer player, Location location)
    {
    	
    	// Set their World
       	World world = getWorldByName(location.getWorld());
    	if(world == null) // This bit is Kludgy... would like to rework.
    	{
    		return false;
    	}
    	
    	if(!(player.worldObj.getWorldInfo().getWorldName() == world.getWorldInfo().getWorldName())) // Dont set world if they are already there.
    	{
    		player.setWorld(world);
    	}
                             
        // Set their Dimension
    	if(!(player.dimension == location.getDimension()))
    	{
    		player.travelToDimension(location.getDimension());
    	}
        
        // Set their Position
        player.setPositionAndRotation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        
        // Make sure the position update packet got sent.
        player.setPositionAndUpdate(location.getX(), location.getY(), location.getZ());
        return true;
    }
    
    public World getWorldByName(String name)
    {
    	 for(World world:net.minecraftforge.common.DimensionManager.getWorlds())
         {
         	if(world.getWorldInfo().getWorldName() == name)
         	{
         		return(world);
        	}
         }
    	 return null;
    }
    
    public Home getHomeByOwner(UUID owner)
    {
    	for(Home home:getHomes())
    	{
    		if(home.getOwnerUUID().equals(owner))
    		{
    			return home;
    		}
    	}
    	return null;
    }
    
    public List<Home> getHomes()
    {
    	return this.homes;
    }
    
    public void setHomes(List<Home> homes)
    {
    	this.homes = homes;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void setSpawn(Location spawn) {
		this.spawn = spawn;
	}

	
}
