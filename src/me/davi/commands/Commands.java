package me.davi.commands;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.davi.Main;
import me.davi.abstracts.SubCommand;
import me.davi.commands.sub.SubCmdHelp;
import me.davi.commands.sub.cSubAdd;
import me.davi.commands.sub.cSubCriar;
import me.davi.commands.sub.cSubDeletar;
import me.davi.commands.sub.cSubPay;
import me.davi.commands.sub.cSubRemove;
import me.davi.commands.sub.cSubSet;
import me.davi.commands.sub.cSubToggle;
import me.davi.commands.sub.cSubTop;
import me.davi.manager.Messagens;

public class Commands implements CommandExecutor {

	private List<SubCommand> subcommands;

	public Commands(String command) {
		subcommands = new ArrayList<SubCommand>();
		subcommands.add(new SubCmdHelp(command));
		subcommands.add(new cSubTop(command));
		subcommands.add(new cSubCriar(command));
		subcommands.add(new cSubDeletar(command));
		subcommands.add(new cSubAdd(command));
		subcommands.add(new cSubRemove(command));
		subcommands.add(new cSubSet(command));
		subcommands.add(new cSubPay(command));
		subcommands.add(new cSubToggle(command));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (!Main.economia.existsAccount(sender.getName()))
				Main.economia.createAccount(sender.getName(), new BigDecimal(Messagens.moneyInicial));

		}
		if (args.length >= 1) {
			String arg = args[0].toLowerCase();
			if (!subcommands.isEmpty()) {
				for (SubCommand subCommand : subcommands) {
					if (arg.equalsIgnoreCase(subCommand.getName().toLowerCase())
							|| subCommand.getAlias().contains(arg)) {
						if (sender.hasPermission(subCommand.getPermission()) || subCommand.getPermission().isEmpty()) {
							subCommand.execute(sender, args);
						} else {
							sender.sendMessage(
									"§c§lPERMISSAO §fVocê não tem §c§lPERMISSÃO §fpara executar esse comando!");
						}
						return false;
					}
				}
			}

			if (Main.economia.existsAccount(args[0])) {
				if (sender.getName().equals(args[0])) {
					if (sender instanceof Player) {
						sender.sendMessage(Messagens.money + "Money: §a"
								+ Main.numberFormat(Main.economia.getBalance(sender.getName())));
					} else {
						sender.sendMessage("§a/" + command.getName() + " ajuda §8- §7ver os comandos do plugin.");
					}
				} else {
					sender.sendMessage(Messagens.money + "O jogador §b" + args[0] + "§f possui §a"
							+ Main.numberFormat(Main.economia.getBalance(args[0])));
				}
			} else {
				sender.sendMessage(Messagens.jogadorInvalido);
			}
		} else {
			if (sender instanceof Player) {
				sender.sendMessage(
						Messagens.money + "Money: §a" + Main.numberFormat(Main.economia.getBalance(sender.getName())));
			} else {
				sender.sendMessage("§a/" + command.getName() + " ajuda §8- §7ver os comandos do plugin.");
			}
		}
		return false;
	}
}
