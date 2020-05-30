package me.davi.economy.plugin.objetos;

import org.bukkit.Bukkit;

import me.davi.economy.app.WireEconomy;
import me.davi.economy.manager.Messagens;

public class RefreshMoneyTop implements Runnable {

	private int task;

	public RefreshMoneyTop() {
		task = -1;
		reload();
	}

	@Override
	public void run() {
		WireEconomy.economia.loadMoneyTop();
	}

	public void reload() {
		if (task != -1) {
			Bukkit.getScheduler().cancelTask(task);
		}
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(WireEconomy.instance, this, 10,
				20 * Messagens.ConfigmoneyTopAtualizar);
	}
}
