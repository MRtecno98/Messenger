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
		if(sender instanceof Player) {
			Player p = (Player)sender;
				if(args.length < 2) {
					p.sendMessage(ChatColor.RED + instance.description.getCommands().get("msg").get("tooltip").toString());
					return false;
				}
				if(!(instance.getServer().getPlayer(args[0]).isValid())) {
					p.sendMessage(ChatColor.RED + "Player is not Online!");
				}
				Player t = instance.getServer().getPlayer(args[0]);
				t.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + p.getName() + ChatColor.RESET + " --> " + ChatColor.RED + "You" + ChatColor.GOLD + "] " + args[1]);
				instance.responses.put(p, t);
				return true;
		}
		return false;
	}
}
