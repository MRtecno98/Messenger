package com.messenger.executors;

import it.xquickglare.quicklib.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.messenger.main.Messenger;

public class ResExecutor implements CommandExecutor {
	private Messenger instance;

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
			player.sendMessage(Message.format(instance.configuration.getString("no_recent_message"), '&'));
			return false;
		}

		Player target = instance.responses.get(player);
		if(!(target.isValid())) {
			player.sendMessage(Message.format(instance.configuration.getString("player_not_online"), '&'));
			return false;
		}

		target.sendMessage(ChatColor.GOLD + "[" + ChatColor.GRAY + player.getName() + ChatColor.RESET + " --> " + ChatColor.RED + "You" + ChatColor.GOLD + "] " + args[0]);
		return true;
	}
}
