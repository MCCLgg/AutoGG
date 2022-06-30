package com.podcrash.mod.autogg.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class TestCommand extends CommandBase {
    
    
    @Override
    public String getName( ){
        return "autogg";
    }
    
    @Override
    public String getUsage(ICommandSender sender){
        return "autogg <type>";
    }
    
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException{
        if (args.length == 0) {
            sender.sendMessage(new TextComponentString(" 1st Killer - [MVP+] Barra - 3"));
            return;
        } if (args.length == 1){
            sender.sendMessage(new TextComponentString(" 1st Killer - [MVP+] Test - 2"));
            
        }
    }
}
