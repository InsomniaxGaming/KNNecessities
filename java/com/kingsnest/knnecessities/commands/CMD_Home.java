package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Home;
import com.kingsnest.knnecessities.datatypes.Location;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class CMD_Home implements ICommand {
    private KNNecessities_Main myMod       = null;
    private String             commandName = "home";
    private String             commandUse  = "/home | Will take you to the home you set with /sethome, or spawn if you have none.";

    private List               aliases;

    public CMD_Home(KNNecessities_Main instance) {
        this.myMod = instance;
        this.aliases = new ArrayList();
        aliases.add("home");
        aliases.add("h");
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

        if (icommandsender instanceof EntityPlayer) { // Woo its a player
            player = (EntityPlayer) icommandsender;

            // Check for home
            NBTTagCompound nbt = player.getEntityData();
            NBTBase worldTag = nbt.getTag(myMod.WORLDKEY);

            if (worldTag != null) {
                // Home found in NBT!
                // Build Location from NBT tags.
                Location loc = new Location(nbt.getString(myMod.WORLDKEY),
                        nbt.getInteger(myMod.DIMENSIONKEY),
                        nbt.getDouble(myMod.XKEY), nbt.getDouble(myMod.YKEY),
                        nbt.getDouble(myMod.ZKEY),
                        nbt.getFloat(myMod.PITCHKEY),
                        nbt.getFloat(myMod.YAWKEY));

                // Try to send them to the place named.
                if (myMod.sendPlayerToLocation(player, loc)) {
                    cmc.appendText("The magical space beavers found your home, and whisk you away to it.");

                } else {
                    cmc.appendText("Due to great upheavel in the cosmic energy fields the magical space beavers were not able to take you to your home. You may want to tell someone about this.");
                }

                player.addChatMessage(cmc);
                return;

            } else {
                // No Home data found in Player's NBT... No big deal...
                cmc.appendText("If we actually could take you home, we would.");
                cmc.appendText("The magical space beavers are anxious to fulfill your command but can not.");
                player.addChatMessage(cmc);
                return;
            }
        } else { // Silly consoles.
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
