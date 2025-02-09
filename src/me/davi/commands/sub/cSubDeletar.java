package me.davi.commands.sub;

import org.bukkit.command.CommandSender;

import me.davi.Main;
import me.davi.abstracts.SubCommand;
import me.davi.manager.Messagens;

public class cSubDeletar extends SubCommand {

	public cSubDeletar(String command) {
		super("deletar", Messagens.money + "�fUse correto: �b/" + command + " deletar (nome)", "rankup.cmd.deletar",
				"delete", "del");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 2) {
			String nome = args[1];
			if (Main.economia.deleteAccount(nome)) {
				sender.sendMessage(Messagens.money + "Conta �b" + nome + "�f deletada com �a�lSUCESSO�f!");
			} else {
				sender.sendMessage(Messagens.money + "N�o existe uma conta com o nome �c" + nome);
			}
		} else {
			sender.sendMessage(getUsage());
		}
	}
}
