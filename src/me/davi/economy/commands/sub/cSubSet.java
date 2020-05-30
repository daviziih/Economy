package me.davi.economy.commands.sub;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.app.WireEconomy;
import me.davi.economy.manager.Messagens;

public class cSubSet extends SubCommand {

	public cSubSet(String command) {
		super("set", Messagens.money + "Use correto: §b/" + command + " set (jogador) (valor)", "rankup.cmd.set", "definir",
				"setar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];
			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 0) {
				sender.sendMessage(Messagens.numeroInvalido);
				return;
			}

			if (WireEconomy.economia.setBalance(nome, valor)) {
				sender.sendMessage(
						Messagens.money + "Você setou §a" + WireEconomy.numberFormat(valor) + "§f na conta de §b" + nome);
			} else {
				sender.sendMessage(Messagens.jogadorInvalido);
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
