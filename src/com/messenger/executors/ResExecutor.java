package com.messenger.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.messenger.main.Messenger;

public class ResExecutor implements CommandExecutor {
	Messenger instance;
	
	public ResExecutor(Messenger instance) {
		this.instance = instance;
	}
	
	public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(label.equalsIgnoreCase("r")) {
				if(args.length < 1) {
					p.sendMessage(ChatColor.RED + instance.description.getCommands().get("r").get("tooltip").toString());
					return false;
				}
				if(!(instance.responses.containsKey(p))) {
					p.sendMessage(ChatColor.RED + "You don't have recent messages!");
					return false;
				}
				Player t = instance.responses.get(p);
				if(!(t.isValid())) {
					p.sendMessage(ChatColor.RED + "Player is not Online!");
					return false;
				}
				t.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + p.getName() + ChatColor.RESET + " --> " + ChatColor.RED + "You" + ChatColor.GOLD + "] " + args[0]);
				return true;
			}
		}
		return false;
	}
}
