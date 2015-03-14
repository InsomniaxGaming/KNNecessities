package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.datatypes.Location;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CMD_SetHome implements ICommand{
	private String commandName = "home";
	private String commandUse = "/home | Will take you to the home you set with /sethome, or spawn if you have none.";
	
	
	private List aliases;
	
	public CMD_SetHome() {
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
         
         if(icommandsender instanceof EntityPlayer){
                 player = (EntityPlayer)icommandsender;
                 cmc.appendText("If we actually could set your home, we would.");
                 cmc.appendText("The magical space beavers are anxious to fulfill your command but can not.");
                 player.addChatMessage(cmc);
                 
                 Location loc = new Location(player.worldObj.getWorldInfo().getWorldName(), player.dimension, player.posX, player.posY, player.posZ, player.cameraPitch, player.cameraYaw);
                 return;
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
}
