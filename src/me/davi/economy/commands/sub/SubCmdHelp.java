package me.davi.economy.commands.sub;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.manager.Messagens;

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
			sender.sendMessage(Messagens.money + "§f/" + command + " §8- §7ver seu saldo atual.");
			if (sender.hasPermission("cmd.tag.membro"))
				sender.sendMessage(Messagens.money + "§f/" + command + " pay (jogador) (valor) §8- §7envia money a um jogador.");

			if (sender.hasPermission("rankup.cmd.set"))
				sender.sendMessage(Messagens.money + "§f/" + command + " set (jogador) (valor) §8- §7seta o money de um jogador.");

			if (sender.hasPermission("solaryeconomy.commands.add"))
				sender.sendMessage(Messagens.money + "§f/" + command + " add [jogador] [valor] §8- §7adicionar money a um jogador.");

			if (sender.hasPermission("solaryeconomy.commands.remove"))
				sender.sendMessage(Messagens.money + "§f/" + command + " remove [jogador] [valor] §8- §7remove money de um jogador.");

			if (sender.hasPermission("solaryeconomy.commands.top"))
				sender.sendMessage(Messagens.money + "§f/" + command + " top §8- §7ver o money top do servidor.");

			sender.sendMessage("§a/" + command + " ajuda §8- §7ver os comandos do plugin.");

			if (sender.hasPermission("solaryeconomy.commands.toggle"))
				sender.sendMessage(Messagens.money + "§f/" + command + " toggle §8- §7habilitar/desabilitar o recebimento de coins.");

			if (sender.hasPermission("solaryeconomy.commands.criar"))
				sender.sendMessage(Messagens.money + "§f/" + command + " criar (nome) (valor) §8- §7criar uma conta.");

			if (sender.hasPermission("rankup.cmd.deletar"))
				sender.sendMessage(Messagens.money + "§f/" + command + " deletar (nome) §8- §7deletar uma conta.");

		} else {
			sender.sendMessage("§a/" + command + " [jogador]§8- §7ver o saldo atual de um jogador.");
			sender.sendMessage("§a/" + command + " set [jogador] [valor] §8- §7seta o money de um jogador.");
			sender.sendMessage("§a/" + command + " add [jogador] [valor] §8- §7adicionar money a um jogador.");
			sender.sendMessage("§a/" + command + " remove [jogador] [valor] §8- §7remove money de um jogador.");
			sender.sendMessage("§a/" + command + " top §8- §7ver o money top do servidor.");
			sender.sendMessage("§a/" + command + " ajuda §8- §7ver os comandos do plugin.");
			sender.sendMessage("§a/" + command + " create [jogador] [valor] §8- §7criar uma conta.");
			sender.sendMessage("§a/" + command + " delete [jogador] [valor] §8- §7deletar uma conta.");
			sender.sendMessage("§a/" + command + " converter [plugin] §8- §7converter a economia de um plugin.");
			sender.sendMessage("§a/" + command + " reload §8- §7recarregar os arquivos de configs.");
			sender.sendMessage("§a/" + command + " magnata §8- §7ver o magnata atual do servidor.");
		}

		sender.sendMessage(" ");

	}

}
