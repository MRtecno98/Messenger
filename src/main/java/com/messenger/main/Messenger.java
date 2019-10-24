package com.messenger.main;

import com.messenger.executors.MsgExecutor;
import com.messenger.executors.ResExecutor;
import it.xquickglare.quicklib.QuickLib;
import it.xquickglare.quicklib.configuration.YAMLConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Messenger extends JavaPlugin {

	public HashMap<Player , Player> responses = new HashMap<>();
	private Logger logger = getLogger();
	public PluginDescriptionFile description = getDescription();
	public YAMLConfiguration configuration;
	
	@Override
	public void onEnable() {
		QuickLib quickLib = new QuickLib(this);
		configuration = new YAMLConfiguration("config", quickLib);

		this.getCommand("msg").setExecutor(new MsgExecutor(this));
		this.getCommand("r").setExecutor(new ResExecutor(this));
		logger.log(Level.INFO , ChatColor.GOLD + description.getName() + " - " + description.getVersion() + ChatColor.GREEN + " ACTIVATED");
	}
	
	@Override
	public void onDisable() {
		logger.log(Level.INFO , ChatColor.GOLD + description.getName() + " - " + description.getVersion() + ChatColor.DARK_RED + " DEACTIVATED");
	}
}
