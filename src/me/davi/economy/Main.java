package me.davi.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.davi.economy.app.WireEconomy;
import me.davi.economy.commands.WireCommand;

public class Main extends JavaPlugin {

	public static WireEconomy plugin;

	@Override
	public void onEnable() {
		plugin = new WireEconomy(this);
		plugin.onEnable();
		if (Bukkit.getPluginManager().isPluginEnabled(this)) {
			getCommand("money").setExecutor(new WireCommand("money"));
		}
	}

	@Override
	public void onDisable() {
		plugin.onDisable();
	}
}
