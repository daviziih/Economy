package me.davi.economy.app;

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.davi.economy.Main;
import me.davi.economy.manager.Messagens;

public class PlayerJoin implements Listener {

	public PlayerJoin(Main main) {
		Bukkit.getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	private void onJoin(PlayerJoinEvent event) {
		if (!Main.economia.existsAccount(event.getPlayer().getName())) {
			Main.economia.createAccount(event.getPlayer().getName(), new BigDecimal(Messagens.moneyInicial));
		}
	}
}
