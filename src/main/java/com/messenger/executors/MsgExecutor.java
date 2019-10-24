package com.messenger.executors;

import com.messenger.main.Messenger;
import it.xquickglare.quicklib.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MsgExecutor implements CommandExecutor {

	private Messenger instance;

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
			player.sendMessage(Message.format(instance.configuration.getString("player_not_online"), '&'));
			return false;
		}

		Player target = instance.getServer().getPlayer(args[0]);

		Message message = new Message(instance.configuration.getString("message_sent"))
				.addPlaceholder("%player", args[0])
				.addPlaceholder("%message", String.join(" ",Arrays.copyOfRange(args, 1, args.length)))
				.format('&');

		message.send(target);

		instance.responses.put(player, target);

		return true;
	}
}
