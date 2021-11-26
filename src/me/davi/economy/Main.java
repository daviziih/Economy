package me.davi.economy;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.davi.economy.commands.Commands;
import me.davi.economy.database.Database;
import me.davi.economy.database.SQLite;
import me.davi.economy.manager.Messagens;
import me.davi.economy.plugin.Economy;
import me.davi.economy.plugin.objetos.RefreshMoneyTop;
import me.davi.economy.plugin.vault.VaultEconomy;
import me.davi.economy.util.Config;

public class Main extends JavaPlugin {

	public static Main plugin;

	public static final String PLUGIN_NAME = "Economy";
	public static Config config;

	public static String table;

	public static Database database;
	public static Economy economia;
	public static RefreshMoneyTop refreshMoneyTop;

	@Override
	public void onEnable() {
		plugin = this;

		if (Bukkit.getPluginManager().isPluginEnabled(this)) {
			getCommand("money").setExecutor(new Commands("money"));
		}

		database();
		
		economia = new Economy();
		refreshMoneyTop = new RefreshMoneyTop();
		
		try {
			Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
			if (vault != null) {
				new VaultEconomy();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		plugin = null;

		if (database != null) {
			if (database.connection()) {
				database.close();
			}
		}
	}

	public void database() {
		try {
			table = PLUGIN_NAME.toLowerCase().replace("-", "");
			database = new SQLite(plugin);

			if (database.open()) {
				database.close();
			} else {
				table = PLUGIN_NAME.toLowerCase().replace("-", "");
				database = new SQLite(plugin);
			}
			database.open();

			database.execute("create table if not exists " + table + " (name varchar(40), valor text, toggle int);");

			database.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String numberFormat(BigDecimal bigDecimal) {
		String formated = "";
		double doubleValue = bigDecimal.doubleValue();
		DecimalFormat decimalFormat = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.GERMAN));
		formated += decimalFormat.format(bigDecimal);

		if (doubleValue >= -1 && doubleValue <= 1)
			formated += " " + Messagens.moneySingular;
		else
			formated += " " + Messagens.moneyPlural;

		return formated;
	}
}
