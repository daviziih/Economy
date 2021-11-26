package me.davi.economy.commands.sub;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import me.davi.economy.Main;
import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.manager.Messagens;

public class cSubRemove extends SubCommand {

	public cSubRemove(String command) {
		super("remove", Messagens.money + "Use correto: §b/" + command + " remove (jogador) (valor)", "rankup.cmd.remove",
				"take");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];
			BigDecimal valor = numbers.getDecimal(args[2]);

			if (valor.doubleValue() <= 0) {
				sender.sendMessage(Messagens.numeroInvalido);
				return;
			}

			if (Main.economia.substractBalance(nome, valor)) {
				sender.sendMessage(Messagens.money + "Você removeu §a" + Main.numberFormat(valor)
						+ "§f da conta de §c" + nome);
			} else {
				sender.sendMessage(Messagens.jogadorInvalido);
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
