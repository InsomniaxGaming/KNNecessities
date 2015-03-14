package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;

public class CMD_ToggleGodMode implements ICommand{
	
    private KNNecessities_Main myMod       = null;
    private String             commandName = "togglegodmode";
    private String             commandUse  = "/togglegodmode | Toggles the gamemode of the command sender.";

    private List               aliases;

    public CMD_ToggleGodMode(KNNecessities_Main instance) {
        this.myMod = instance;
        
        this.aliases = new ArrayList();
        aliases.add("togglegodmode");
        aliases.add("tgm");
    }

	@Override
	public int compareTo(Object arg0)
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return commandName;
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {
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

        if (icommandsender instanceof EntityPlayer) { // We have a player!
            player = (EntityPlayer) icommandsender;
            
            if(player.capabilities.isCreativeMode)
            {
            	player.setGameType(GameType.ADVENTURE);
            	cmc.appendText("Gamemode set to adventure.");
            }
            else
            {
            	player.setGameType(GameType.CREATIVE);
            	cmc.appendText("Gamemode set to creative.");
            }
            
	        
            player.addChatMessage(cmc);
        } else { // Oh that silly Console.
            cmc.appendText("You know, if I didn't have to worry about you using stupid commands, these features would be done faster. Fuck you.");
            icommandsender.addChatMessage(cmc);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        EntityPlayer player;
        if (icommandsender instanceof EntityPlayer) { // We have a player!
            player = (EntityPlayer) icommandsender;
            // Only ops...
            return (myMod.isOp(player));
        }

        return true;
    }

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
			String[] p_71516_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		// TODO Auto-generated method stub
		return false;
	}

}
