package me.davi.economy.manager;

import java.util.List;

import me.davi.economy.Main;
import me.davi.economy.plugin.Economia;
import me.davi.economy.plugin.objetos.Account;

public class API {

	public API() {
		economia = Main.economia;
	}

	private Economia economia;

	public boolean isToggle(String account) {
		return economia.isToggle(account);
	}

	public List<Account> getMoneyTop() {
		return economia.getMoneyTop();
	}
}
