package me.davi.commands.sub;

import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.davi.Main;
import me.davi.abstracts.SubCommand;
import me.davi.manager.Messagens;

public class cSubPay extends SubCommand {

	public cSubPay(String command) {
		super("pay", Messagens.money + "Use correto: §b/" + command + " pay (jogador) (valor)", "cmd.tag.membro",
				"pagar", "enviar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length >= 3) {
			String nome = args[1];

			BigDecimal valor = this.numbers.getDecimal(args[2]);

			if (valor.doubleValue() < 1.0) {
				sender.sendMessage(Messagens.numeroInvalido);
				return;
			}

			if (!(sender instanceof Player))
				return;

			if (sender.getName().equalsIgnoreCase(nome)) {
				sender.sendMessage(Messagens.money + "Você não consegue enviar coins para si mesmo");
				return;
			}

			if (Main.economia.hasBalance(sender.getName(), valor)) {

				if (!Main.economia.isToggle(nome)) {
					if (Main.economia.addBalance(nome, valor)) {
						Main.economia.substractBalance(sender.getName(), valor);
						sender.sendMessage(Messagens.money + "Você enviou §a" + Main.numberFormat(valor)
								+ "§f para o jogador §b" + nome);

						Player target = Bukkit.getPlayer(nome);
						if (target != null) {
							if (sender != target) {
								target.sendMessage(Messagens.money + "O jogador §b" + sender.getName()
										+ "§f acabou de enviar §a" + Main.numberFormat(valor));
							}
						}

					} else {
						sender.sendMessage(Messagens.jogadorInvalido);
					}
				} else {
					sender.sendMessage(Messagens.money + "O jogador está com recebimento de money §c§lDESATIVADO§f!");
				}
			} else {
				sender.sendMessage(Messagens.money + "Você não de money sufuciente para isso");
			}

		} else {
			sender.sendMessage(getUsage());
		}
	}
}
