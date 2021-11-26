package me.davi.economy.commands.sub;

import org.bukkit.command.CommandSender;

import me.davi.economy.Main;
import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.manager.Messagens;

public class cSubDeletar extends SubCommand {

	public cSubDeletar(String command) {
		super("deletar", Messagens.money + "§fUse correto: §b/" + command + " deletar (nome)", "rankup.cmd.deletar",
				"delete", "del");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 2) {
			String nome = args[1];
			if (Main.economia.deleteAccount(nome)) {
				sender.sendMessage(Messagens.money + "Conta §b" + nome + "§f deletada com §a§lSUCESSO§f!");
			} else {
				sender.sendMessage(Messagens.money + "Não existe uma conta com o nome §c" + nome);
			}
		} else {
			sender.sendMessage(getUsage());
		}
	}
}
