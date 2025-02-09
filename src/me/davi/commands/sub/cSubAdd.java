package me.davi.commands.sub;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import me.davi.Main;
import me.davi.abstracts.SubCommand;
import me.davi.manager.Messagens;

public class cSubAdd extends SubCommand {

	public cSubAdd(String command) {
		super("add", Messagens.money + "Use correto: �b/" + command + " add (jogador) (valor)", "rankup.cmd.add",
				"give");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {

			String nome = args[1];

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() <= 0) {
				sender.sendMessage(Messagens.money + "Valor incorreto, por favor insira um valor v�lido.");
				return;
			}

			if (Main.economia.addBalance(nome, valor)) {
				sender.sendMessage(Messagens.money + "Foi adicionado na conta de �b" + nome + "�f o valor �a"
						+ Main.numberFormat(valor));
			} else {
				sender.sendMessage(Messagens.money + "Jogador n�o encontrado");
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
