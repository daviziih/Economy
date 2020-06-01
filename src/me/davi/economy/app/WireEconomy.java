package me.davi.economy.app;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.davi.economy.database.Database;
import me.davi.economy.database.SQLite;
import me.davi.economy.manager.Messagens;
import me.davi.economy.plugin.Economia;
import me.davi.economy.plugin.objetos.RefreshMoneyTop;
import me.davi.economy.plugin.vault.VaultEconomy;
import me.davi.economy.util.Config;

public class WireEconomy implements Listener {

	public WireEconomy(JavaPlugin plugin) {
		instance = plugin;
	}

	public static final String PLUGIN_NAME = "WireEconomy";
	public static Config config;

	public static String table;

	public static JavaPlugin instance;
	public static Database database;
	public static Economia economia;
	public static RefreshMoneyTop refreshMoneyTop;

	public void onEnable() {
		database();
		economia = new Economia();
		config = new Config(instance, "config.yml");
		refreshMoneyTop = new RefreshMoneyTop();
		instance.getServer().getPluginManager().registerEvents(this, instance);
		try {
			Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
			if (vault != null) {
				new VaultEconomy();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void onDisable() {
		if (database != null) {
			if (database.connection()) {
				database.close();
			}
		}

	}

	public void database() {
		try {
			table = PLUGIN_NAME.toLowerCase().replace("-", "");
			database = new SQLite(instance);

			if (database.open()) {
				database.close();
			} else {
				table = PLUGIN_NAME.toLowerCase().replace("-", "");
				database = new SQLite(instance);
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

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!economia.existsAccount(event.getPlayer().getName())) {
			economia.createAccount(event.getPlayer().getName(), new BigDecimal(Messagens.moneyInicial));
		}
	}
}
