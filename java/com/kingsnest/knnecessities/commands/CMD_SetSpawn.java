package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Location;

public class CMD_SetSpawn implements ICommand{
	private KNNecessities_Main myMod = null;
	private String commandName = "setspawn";
	private String commandUse = "/setspawn | Sets the location that will be teleported to when using /spawn.";
		
	
	private List aliases;
	
	public CMD_SetSpawn(KNNecessities_Main instance) {
		this.myMod = instance;
		this.aliases = new ArrayList();
		aliases.add("setspawn");
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
        	 
        	 //Should have permission if they made it this far... so just do eet.
        	 myMod.setSpawn(new Location(player.worldObj.getWorldInfo().getWorldName(), player.dimension, player.posX, player.posY, player.posZ, player.cameraPitch, player.cameraYaw));
        	 cmc.appendText("Undead nether squirrels record the location of /spawn.");
        	 player.addChatMessage(cmc);
         } else { //Oh that silly Console.
        	 cmc.appendText("Consoles can't set spawns. Nice try though. Also the squirrels say, 'Fuck You!'");
        	 icommandsender.addChatMessage(cmc);
         }
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		EntityPlayer player;
		if(icommandsender instanceof EntityPlayer){ // We have a player!
			player = (EntityPlayer)icommandsender;
			//Only ops...
			return(myMod.isOp(player));
		}
		
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