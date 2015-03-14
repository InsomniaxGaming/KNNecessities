package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Home;
import com.kingsnest.knnecessities.datatypes.Location;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;

public class CMD_SetHome implements ICommand {
    private KNNecessities_Main myMod       = null;
    private String             commandName = "sethome";
    private String             commandUse  = "/sethome | Sets the location you will be sent to when using /home.";

    private List               aliases;

    public CMD_SetHome(KNNecessities_Main instance) {
        this.myMod = instance;
        this.aliases = new ArrayList();
        aliases.add("sethome");
    }

    @Override
    public int compareTo(Object arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return commandUse;
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring) {
        EntityPlayer player;
        ChatComponentText cmc = new ChatComponentText("");

        if (icommandsender instanceof EntityPlayer) {
            // We have a player!
            player = (EntityPlayer) icommandsender;

            // Check for home
            NBTTagCompound nbt = player.getEntityData();
            NBTBase worldTag = nbt.getTag(myMod.WORLDKEY);

            if (worldTag != null) {
                // House found, inform them of its deletion...
                cmc.appendText("The magical space beavers have erased your old home from their logs. ");
            }

            // Go ahead and set their house up.
            nbt.setString(myMod.WORLDKEY, player.worldObj.getWorldInfo()
                    .getWorldName()); // World
            nbt.setInteger(myMod.DIMENSIONKEY, player.dimension); // Dimension
            nbt.setDouble(myMod.XKEY, player.posX); // X
            nbt.setDouble(myMod.YKEY, player.posY); // Y
            nbt.setDouble(myMod.ZKEY, player.posZ); // Z
            nbt.setFloat(myMod.PITCHKEY, player.cameraPitch); // Pitch
            nbt.setFloat(myMod.YAWKEY, player.cameraYaw); // Yaw

            cmc.appendText("The magical space beavers have noted the location of your new home.");
            player.addChatMessage(cmc);
            return;
        } else { // Silly console
            cmc.appendText("No. Fuck you.");
            icommandsender.addChatMessage(cmc);
            return;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        // Well... since we have no clue how we are handling perms... Sure. :)
        return true;
    }

    @Override
    public List addTabCompletionOptions(ICommandSender icommandsender,
            String[] astring) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] astring, int i) {
        // TODO Auto-generated method stub
        return false;
    }
}
