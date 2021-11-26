package me.davi.commands.sub;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import me.davi.Main;
import me.davi.abstracts.SubCommand;
import me.davi.manager.Messagens;

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

			if (Main.economia.setBalance(nome, valor)) {
				sender.sendMessage(
						Messagens.money + "Você setou §a" + Main.numberFormat(valor) + "§f na conta de §b" + nome);
			} else {
				sender.sendMessage(Messagens.jogadorInvalido);
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
