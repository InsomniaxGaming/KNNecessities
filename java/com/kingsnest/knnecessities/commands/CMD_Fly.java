package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings.GameType;

public class CMD_Fly implements ICommand{
	
    private KNNecessities_Main myMod       = null;
    private String             commandName = "fly";
    private String             commandUse  = "/fly | Become a bird! (not really)";

    private List               aliases;

    public CMD_Fly(KNNecessities_Main instance) {
        this.myMod = instance;
        
        this.aliases = new ArrayList();
        aliases.add("fly");
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
            
            player.capabilities.allowFlying = !player.capabilities.allowFlying;
            cmc.appendText("Flying toggled.");
	        
            player.addChatMessage(cmc);
        } else { // Oh that silly Console.
            cmc.appendText("This one's easy.. Throw the server out the nearest window, and fuck you.");
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

