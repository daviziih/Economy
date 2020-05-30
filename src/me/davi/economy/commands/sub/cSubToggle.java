package me.davi.economy.commands.sub;

import org.bukkit.command.CommandSender;

import me.davi.economy.abstracts.SubCommand;
import me.davi.economy.app.WireEconomy;
import me.davi.economy.manager.Messagens;

public class cSubToggle extends SubCommand {

	public cSubToggle(String command) {
		super("toggle", Messagens.money + "Use correto: §b/" + command + " toggle", "cmd.tag.membro");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		String toggle = WireEconomy.economia.toggle(sender.getName()) ? "OFF" : "ON";
		sender.sendMessage(Messagens.money + "Recebimento de money " + toggle);

	}
}
