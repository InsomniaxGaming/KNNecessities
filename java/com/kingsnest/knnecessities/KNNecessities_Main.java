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
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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

@Mod(modid = KNNecessities_Main.MODID, version = KNNecessities_Main.VERSION, name = KNNecessities_Main.NAME)
public class KNNecessities_Main {

    // Static stoof.
    public static final String   MODID        = "knnecessities";
    public static final String   VERSION      = "0.0.1 Alpha";
    public static final String   NAME         = "King's Nest Necessary Commands";

    public static final String   WORLDKEY     = "KNN.Home.World";
    public static final String   DIMENSIONKEY = "KNN.Home.Dimension";
    public static final String   XKEY         = "KNN.Home.X";
    public static final String   YKEY         = "KNN.Home.Y";
    public static final String   ZKEY         = "KNN.Home.Z";
    public static final String   PITCHKEY     = "KNN.Home.Pitch";
    public static final String   YAWKEY       = "KNN.Home.Yaw";

    @Instance(MODID)
    public KNNecessities_Main    instance;

    @SidedProxy(clientSide = "com.kingsnest.knnecessities.KNNClientProxy", serverSide = "com.kingsnest.knnecessities.KNNCommonProxy")
    public static KNNClientProxy proxy;

    // Spawn point
    private Location             spawn;

    // Temporary list of homes awaiting persistence on respawn.
    private List<Home>           respawns;

    // Config object
    public Configuration         config       = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        respawns = new ArrayList<Home>();

        config = new Configuration(event.getSuggestedConfigurationFile());

        // loading the configuration from its file
        config.load();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Just to get our name in the log. :P
        System.out.println(NAME + " v" + VERSION + " loading.");

    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CMD_Home(this)); // Add /home handler
        event.registerServerCommand(new CMD_SetHome(this)); // Add /sethome
                                                            // handler
        return;
    }

    @EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        // Save the Spawn.
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.World", getSpawn().getWorld());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.Dimension", getSpawn().getDimension());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.X", getSpawn().getX());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.Y", getSpawn().getY());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.Z", getSpawn().getZ());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.Pitch", getSpawn().getPitch());
        config.get(Configuration.CATEGORY_GENERAL, "Spawn.Yaw", getSpawn().getYaw());

        config.save();
        return;
    }

    @EventHandler
    public void livingDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayer) {
            // Player death
            EntityPlayer player = (EntityPlayer) event.entity;
            // Check for home
            NBTTagCompound nbt = player.getEntityData();
            NBTBase worldTag = nbt.getTag(WORLDKEY);
            if (worldTag != null) {
                // Home found in NBT!
                // Build Location from NBT tags.
                Location loc = new Location(nbt.getString(WORLDKEY),
                        nbt.getInteger(DIMENSIONKEY), nbt.getDouble(XKEY),
                        nbt.getDouble(YKEY), nbt.getDouble(ZKEY),
                        nbt.getFloat(PITCHKEY), nbt.getFloat(YAWKEY));

                Home home = new Home(loc, player.getUniqueID());

                // Persist the home to our variable, and pray they respawn.
                respawns.add(home);
            }
        }
    }

    @EventHandler
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer) {
            // Player join, check to see if we have a pending NBT for them.
            EntityPlayer player = (EntityPlayer) event.entity;

            // Check for home
            NBTTagCompound nbt = player.getEntityData();
            NBTBase worldTag = nbt.getTag(WORLDKEY);

            if (worldTag != null) {
                // Home probably found... IE we should uh... just walk away.
                return;
            } else {
                for (Home home : this.respawns) {
                    if (home.getOwnerUUID().equals(player.getUniqueID())) {
                        // Copy their data back to them.
                        Location loc = home.getLocation();
                        nbt.setString(WORLDKEY, loc.getWorld()); // World
                        nbt.setInteger(DIMENSIONKEY, loc.getDimension()); // Dimension
                        nbt.setDouble(XKEY, loc.getX()); // X
                        nbt.setDouble(YKEY, loc.getY()); // Y
                        nbt.setDouble(ZKEY, loc.getZ()); // Z
                        nbt.setFloat(PITCHKEY, loc.getPitch()); // Pitch
                        nbt.setFloat(YAWKEY, loc.getYaw()); // Yaw

                        // And remove this entry in respawns.
                        respawns.remove(home);
                        return;
                    }
                }
            }
        }
    }

    public boolean sendPlayerToLocation(EntityPlayer player, Location location) {

        // Set their World
        World world = getWorldByName(location.getWorld());
        if (world == null) // This bit is Kludgy... would like to rework.
        {
            return false;
        }

        if (!(player.worldObj.getWorldInfo().getWorldName() == world
                .getWorldInfo().getWorldName())) // Dont set world if they are
                                                 // already there.
        {
            player.setWorld(world);
        }

        // Set their Dimension
        if (!(player.dimension == location.getDimension())) {
            player.travelToDimension(location.getDimension());
        }

        // Set their Position
        player.setPositionAndRotation(location.getX(), location.getY(),
                location.getZ(), location.getPitch(), location.getYaw());

        // Make sure the position update packet got sent.
        player.setPositionAndUpdate(location.getX(), location.getY(),
                location.getZ());
        return true;
    }

    public World getWorldByName(String name) {
        for (World world : net.minecraftforge.common.DimensionManager
                .getWorlds()) {
            if (world.getWorldInfo().getWorldName() == name) {
                return (world);
            }
        }
        return null;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public boolean isOp(EntityPlayer player) {
        // Check if multiplayer and OP or singleplayer and commands allowed
        // (cheats)
        return MinecraftServer.getServer().getConfigurationManager()
                .func_152596_g(player.getGameProfile());
    }

}
