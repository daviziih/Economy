package me.davi.economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.davi.economy.app.Caixa;
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
		Caixa.box("Utils", "Abrindo as conexoes necessarias para o funcionamento correto do plugin.", "",
				"Tentado conectar conexao segundaria", "", "Plugin de economia HABILITADO",
				"Processo de inicializacao! 1/3");
	}

	@Override
	public void onDisable() {
		plugin.onDisable();
	}
}
