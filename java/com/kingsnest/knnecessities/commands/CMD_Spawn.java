package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Location;

public class CMD_Spawn  implements ICommand{
	private KNNecessities_Main myMod = null;
	private String commandName = "spawn";
	private String commandUse = "/spawn | Takes you to 'Spawn'";
		
	private List aliases;
	
	public CMD_Spawn(KNNecessities_Main instance) {
		this.myMod = instance;
		this.aliases = new ArrayList();
		aliases.add("spawn");
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
        	 
        	 myMod.sendPlayerToLocation(player, myMod.getSpawn());
        	 
        	 cmc.appendText("Undead nether squirrels transport you to 'Spawn'.");
        	 player.addChatMessage(cmc);
         } else { //Oh that silly Console.
        	 cmc.appendText("Consoles can't spawn. Nice try though. Also the squirrels say, 'Fuck You!'");
        	 icommandsender.addChatMessage(cmc);
         }
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
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
