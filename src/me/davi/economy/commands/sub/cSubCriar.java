package me.davi.economy.commands.sub;

import java.math.BigDecimal;

import org.bukkit.command.CommandSender;

import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.app.WireEconomy;
import me.davi.economy.manager.Messagens;

public class cSubCriar extends SubCommand {

	public cSubCriar(String command) {
		super("criar", Messagens.money + "Use correto: §b/" + command + " criar (jogador) (valor)", "rankup.cmd.criar",
				"create", "new");
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

			if (WireEconomy.economia.createAccount(nome, valor)) {
				sender.sendMessage(Messagens.money + "Conta §b" + nome + "§f criada com §a§lSUCESSO§f!");

			} else {
				sender.sendMessage(Messagens.money + "Já existe uma conta criada com o nome §c" + nome);
			}

		} else {
			sender.sendMessage(getUsage());
		}

	}

}
