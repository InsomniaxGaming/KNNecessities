package com.kingsnest.knnecessities;

// Java imports
import java.util.ArrayList;
import java.util.List;

// KN imports
import com.kingsnest.knnecessities.datatypes.Home;
import com.kingsnest.knnecessities.commands.CMD_Home;
import com.kingsnest.knnecessities.commands.CMD_SetHome;

// Minecraft / Forge imports
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

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
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
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
    	event.registerServerCommand(new CMD_SetHome(this)); // Add /home handler
    	return;
    }
    
    public List<Home> getHomes()
    {
    	return this.homes;
    }
    
    public void setHomes(List<Home> homes)
    {
    	this.homes = homes;
	}

}
