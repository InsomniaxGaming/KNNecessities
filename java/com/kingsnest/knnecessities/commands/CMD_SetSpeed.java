package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class CMD_SetSpeed implements ICommand{
	
    private KNNecessities_Main myMod       = null;
    private String             commandName = "setspeed";
    private String             commandUse  = "/setspeed [speed] | Sets the speed of the command sender.";

    private List               aliases;

    public CMD_SetSpeed(KNNecessities_Main instance) {
        this.myMod = instance;
        
        this.aliases = new ArrayList();
        aliases.add("setspeed");
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

            if(astring.length > 0)
            {
            	float speed;
            	
            	try {
            		speed = Float.parseFloat(astring[0]);
            	} catch(Exception e) {
            		speed = 1;
            	}

	            player.capabilities.setPlayerWalkSpeed(speed);
	            player.capabilities.setFlySpeed(speed);
	            
	            cmc.appendText("Binarius hath accepted thy request to set speed to " + speed);
            }
            else
            {
	            player.capabilities.setPlayerWalkSpeed(1);
	            player.capabilities.setFlySpeed(1);
	            
            	cmc.appendText("Speed set to default.");
            }
	        
            player.addChatMessage(cmc);
        } else { // Oh that silly Console.
            cmc.appendText("What are you trying to do, overclock the CPU? [Obligatory 'fuck you']");
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
