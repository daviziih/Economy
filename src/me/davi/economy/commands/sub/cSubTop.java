package me.davi.economy.commands.sub;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.app.WireEconomy;
import me.davi.economy.manager.Messagens;
import me.davi.economy.plugin.objetos.Account;

public class cSubTop extends SubCommand {

	public cSubTop(String command) {
		super("top", Messagens.money + "Use correto: §b/" + command + " top", "cmd.tag.membro", "rank");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<Account> moneytop = WireEconomy.economia.getMoneyTop();

		if (!moneytop.isEmpty()) {
			int i = 1;
			sender.sendMessage("§2Top 10 Mais Ricos");
			sender.sendMessage(" ");
			for (Account account : moneytop) {
				String valor = WireEconomy.numberFormat(account.getBalance());
				String accountname = account.getName();
				if (i == 1) {
					sender.sendMessage("§2§l" + i + "§7 " + accountname + ": §a" + valor);
				} else {
					sender.sendMessage("§2§l" + i + "§7 " + accountname + ": §a" + valor);
				}

				i++;
			}
			sender.sendMessage(" ");

		} else {
			sender.sendMessage("&2Top 10 Mais Ricos &7(Atualizado de 5 em 5 minutos)");
			sender.sendMessage(" ");
			sender.sendMessage("&cNão existe jogadores cadastrados ainda");
			sender.sendMessage(" ");
		}

	}

}
