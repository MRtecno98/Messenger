package com.messenger.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.messenger.main.Messenger;

public class MsgExecutor implements CommandExecutor {
	Messenger instance;
	
	public MsgExecutor(Messenger instance) {
		this.instance = instance;
	}
	
	public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		if(args.length < 2) {
			player.sendMessage(ChatColor.RED + instance.description.getCommands().get("msg").get("tooltip").toString());
			return false;
		}
		if(!(instance.getServer().getPlayer(args[0]).isValid())) {
			player.sendMessage(ChatColor.RED + "Player is not Online!");
			return false;
		}
		Player target = instance.getServer().getPlayer(args[0]);
		target.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + player.getName() + ChatColor.RESET + " --> " + ChatColor.RED + "You" + ChatColor.GOLD + "] " + args[1]);
		instance.responses.put(player, target);
		return true;
	}
}
