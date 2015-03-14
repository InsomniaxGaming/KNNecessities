package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Home;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CMD_Home implements ICommand {
	private KNNecessities_Main myPlugin = null;
	private String commandName = "home";
	private String commandUse = "/home | Will take you to the home you set with /sethome, or spawn if you have none.";
	
	
	private List aliases;
	
	public CMD_Home(KNNecessities_Main instance) {
		this.myPlugin = instance;
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
         
         if(icommandsender instanceof EntityPlayer){
                 player = (EntityPlayer)icommandsender;
                 // Check for home
                 Home home = null;
                 home = checkForHome(player.getUniqueID());
                 if(home == null)
                 {
                	 // No home for them!
                     cmc.appendText("If we actually could take you home, we would.");
                     cmc.appendText("The magical space beavers are anxious to fulfill your command but can not.");
                     player.addChatMessage(cmc);
                     return;
                 } else {
                	 // We found a home for them!
                     cmc.appendText("The magical space beavers found your home, and whisk you away to it.");
                     player.addChatMessage(cmc);
                     
                     // Set their World
                     //player.setWorld(p_70029_1_);
                     
                     // Set their Dimension
                     player.dimension = home.getLocation().getDimension();
                     
                     // Set their Position
                     player.setPositionAndRotation(home.getLocation().getX(), home.getLocation().getY(), home.getLocation().getZ(), home.getLocation().getPitch(), home.getLocation().getYaw());
                     return;
                 }
         }
         else {
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
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Home checkForHome(UUID playerUUID)
	{
		return null;
	}
}
