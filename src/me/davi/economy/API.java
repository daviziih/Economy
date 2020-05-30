package me.davi.economy;

import java.util.List;

import me.davi.economy.app.WireEconomy;
import me.davi.economy.plugin.Economia;
import me.davi.economy.plugin.objetos.Account;

public class API {

	public API() {
		economia = WireEconomy.economia;
	}

	private Economia economia;

	public boolean isToggle(String account) {
		return economia.isToggle(account);
	}

	public List<Account> getMoneyTop() {
		return economia.getMoneyTop();
	}
}
