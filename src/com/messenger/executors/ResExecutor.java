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
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		if(args.length < 1) {
			player.sendMessage(ChatColor.RED + instance.description.getCommands().get("r").get("tooltip").toString());
			return false;
		}
		if(!(instance.responses.containsKey(player))) {
			player.sendMessage(ChatColor.RED + "You don't have recent messages!");
			return false;
		}
		Player target = instance.responses.get(player);
		if(!(target.isValid())) {
			player.sendMessage(ChatColor.RED + "Player is not Online!");
			return false;
		}
		target.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + player.getName() + ChatColor.RESET + " --> " + ChatColor.RED + "You" + ChatColor.GOLD + "] " + args[0]);
		return true;
	}
}
