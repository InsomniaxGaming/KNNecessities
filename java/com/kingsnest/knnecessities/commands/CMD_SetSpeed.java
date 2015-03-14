package com.kingsnest.knnecessities.commands;

import java.util.ArrayList;
import java.util.List;

import com.kingsnest.knnecessities.KNNecessities_Main;
import com.kingsnest.knnecessities.datatypes.Location;

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
        aliases.add("setspawn");
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
            	int speed;
            	
            	try {
            		speed = Integer.parseInt(astring[0]);
            	} catch(Exception e) {
            		speed = 1;
            	}
            	
	            player.setVelocity(speed, speed, speed); //TODO figure out wtf this means, or if setVelocity is even the correct method.
	            
	            cmc.appendText("Binarius hath accepted thy request to set speed to " + speed);
            }
            else
            {
	            player.setVelocity(1, 1, 1); //TODO figure out wtf this means, or if setVelocity is even the correct method.
	            
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
