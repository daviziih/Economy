package me.davi.manager;

import java.util.List;

import me.davi.Main;
import me.davi.plugin.Economy;
import me.davi.plugin.objetos.Account;

public class API {

	public API() {
		economia = Main.economia;
	}

	private Economy economia;

	public boolean isToggle(String account) {
		return economia.isToggle(account);
	}

	public List<Account> getMoneyTop() {
		return economia.getMoneyTop();
	}
}
