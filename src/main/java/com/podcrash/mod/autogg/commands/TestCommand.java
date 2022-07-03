package com.podcrash.mod.autogg.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class TestCommand extends CommandBase {
    
    
    @Override
    public String getCommandName( ){
        return "autogg";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender){
        return "autogg <type>";
    }
    
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException{
        if (args.length == 0){
            sender.addChatMessage(new ChatComponentText(" 1st Killer - [MVP+] Barra - 3"));
            return;
        }
        if (args.length == 1){
            sender.addChatMessage(new ChatComponentText(" 1st Killer - [MVP+] Test - 2"));
            
        }
    }
}
