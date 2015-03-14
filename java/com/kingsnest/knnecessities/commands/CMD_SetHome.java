package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Home;
import com.kingsnest.knnecessities.datatypes.Location;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CMD_SetHome implements ICommand{
	private KNNecessities_Main myMod = null;
	private String commandName = "sethome";
	private String commandUse = "/sethome | Sets the location you will be sent to when using /home.";
	
	
	private List aliases;
	
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
         
         if(icommandsender instanceof EntityPlayer){ // We have a player!
                 player = (EntityPlayer)icommandsender;
                 // Get their current Location.
                 Location loc = new Location(player.worldObj.getWorldInfo().getWorldName(), player.dimension, player.posX, player.posY, player.posZ, player.cameraPitch, player.cameraYaw);
                 
                 // Check for a home...
                 Home home = myMod.getHomeByOwner(player.getUniqueID());
                 
                 if(!(home == null))
                 {	
                	 // House found, remove it first
                	 myMod.getHomes().remove(home);
                	 cmc.appendText("The magical space beavers have erased your old home from their logs.\n");
                 } 
                 
                 // Go ahead and add one for them.
            	 myMod.getHomes().add(new Home(loc, player.getUniqueID()));
       
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
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		// TODO Auto-generated method stub
		return false;
	}
}
