package me.davi.economy.plugin;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.davi.economy.Main;
import me.davi.economy.database.Database;
import me.davi.economy.manager.Messagens;
import me.davi.economy.plugin.objetos.Account;

public class Economia {

	public static void main(String[] args) {
		BigDecimal saldo = new BigDecimal("18");

		saldo = saldo.add(new BigDecimal("300"));

		System.out.println(saldo.toPlainString());
	}

	private Map<String, Account> accounts;
	private List<Account> moneytop;

	public Economia() {
		this.accounts = new HashMap<>();
		this.load();
	}

	public boolean createAccount(String name, BigDecimal valor) {
		if (!this.accounts.containsKey(name)) {
			Account account = new Account(name, valor);
			account.save();
			this.accounts.put(name, account);
			return true;
		}
		return false;
	}

	public boolean deleteAccount(String name) {
		if (this.accounts.containsKey(name)) {
			this.accounts.get(name).delete();
			this.accounts.remove(name);
			return true;
		}
		return false;
	}

	public BigDecimal getBalance(String name) {
		try {
			return this.accounts.get(name).getBalance();
		} catch (Exception exception) {
			return new BigDecimal(0.0);
		}
	}

	public boolean setBalance(String name, BigDecimal valor) {
		if (this.accounts.containsKey(name)) {
			this.accounts.get(name).setBalance(valor);
			this.accounts.get(name).saveAsync(20);
			return true;
		}
		return false;
	}

	public boolean addBalance(String name, BigDecimal valor) {
		return this.setBalance(name, this.getBalance(name).add(valor));
	}

	public boolean substractBalance(String name, BigDecimal valor) {
		return this.setBalance(name, this.getBalance(name).subtract(valor));
	}

	public boolean hasBalance(String name, BigDecimal balance) {
		try {
			return this.accounts.get(name).getBalance().doubleValue() >= balance.doubleValue();
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean existsAccount(String name) {
		return this.accounts.containsKey(name);
	}

	public boolean toggle(String name) {
		if (this.accounts.containsKey(name)) {
			Account account = this.accounts.get(name);
			if (account.isToggle()) {
				account.setToggle(false);
			} else {
				account.setToggle(true);
			}
			return account.isToggle();
		}
		return false;
	}

	public boolean isToggle(String name) {
		if (this.accounts.containsKey(name))
			return this.accounts.get(name).isToggle();
		return false;
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}

	public List<Account> getMoneyTop() {
		return this.moneytop;
	}

	public void load() {
		Database database = Main.database;
		database.open();
		try {
			ResultSet result = database.query("select * from " + Main.table);
			while (result.next()) {
				try {
					Account account = Account.valueOf(result);
					if (account != null) {
						this.accounts.put(account.getName(), account);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		database.close();

		this.loadMoneyTop();
	}

	public List<Account> loadMoneyTop() {
		Database database = Main.database;
		this.moneytop = new ArrayList<>();
		database.open();
		try {

			ResultSet result = database.query(
					"select * from " + Main.table.concat(" where length(name) <= " + Messagens.ConfigmoneyTopSize
							+ " order by cast(valor as decimal) desc limit " + Messagens.ConfigmoneyTop + ";"));

			while (result.next()) {
				try {
					Account account = Account.valueOf(result);
					if (account != null) {
						this.moneytop.add(account);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		database.close();

		return this.moneytop;
	}
}
