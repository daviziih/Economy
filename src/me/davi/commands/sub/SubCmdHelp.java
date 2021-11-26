package me.davi.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.davi.abstracts.SubCommand;
import me.davi.manager.Messagens;

public class SubCmdHelp extends SubCommand {

	private String command;

	public SubCmdHelp(String command) {
		super("help", Messagens.money + "Use correto: §b/" + command + " ajuda", "", "ajuda", "?");
		this.command = command;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(" ");

		if (sender instanceof Player) {
			
			sender.sendMessage(" ");
			sender.sendMessage("§b§lMONEY HELP");
			sender.sendMessage(" ");
			
			sender.sendMessage("§7/" + command + " §8- §fver seu saldo atual.");
			
			if (sender.hasPermission("cmd.tag.membro"))
				sender.sendMessage("§7/" + command + " pay (jogador) (valor) §8- §fenvia money a um jogador.");

			if (sender.hasPermission("rankup.cmd.set"))
				sender.sendMessage("§7/" + command + " set (jogador) (valor) §8- §fseta o money de um jogador.");

			if (sender.hasPermission("rankup.commands.add"))
				sender.sendMessage("§7/" + command + " add [jogador] [valor] §8- §fadicionar money a um jogador.");

			if (sender.hasPermission("rankup.commands.remove"))
				sender.sendMessage("§7/" + command + " remove [jogador] [valor] §8- §fremove money de um jogador.");

			if (sender.hasPermission("rankup.commands.top"))
				sender.sendMessage("§7/" + command + " top §8- §fver o money top do servidor.");

			if (sender.hasPermission("rankup.commands.toggle"))
				sender.sendMessage("§7/" + command + " toggle §8- §fhabilitar/desabilitar o recebimento de coins.");

			if (sender.hasPermission("rankup.commands.criar"))
				sender.sendMessage("§7/" + command + " criar (nome) (valor) §8- §fcriar uma conta.");

			if (sender.hasPermission("rankup.cmd.deletar"))
				sender.sendMessage("§7/" + command + " deletar (nome) §8- §fdeletar uma conta.");

		}

		sender.sendMessage(" ");

	}

}
