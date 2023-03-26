package fr.dark.stats.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.dark.stats.Stats;

public class statsCommand implements CommandExecutor {

	Stats core;
	public statsCommand(Stats stats) {this.core = stats;}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(core.joinTime.containsKey(p)) {
					long now = System.currentTimeMillis();
					long first = now - core.joinTime.get(p) + Long.parseLong(Stats.sql.getSpendTime(p.getUniqueId().toString()));
					
					String timePlayed = core.timePlayer(first);
					String moyenne = core.moyenne(first);
					int broken = core.broken.get(p) + Stats.sql.getBrokenBlocks(p.getUniqueId().toString());
					int placed = core.placed.get(p) + Stats.sql.getPlacedBlocks(p.getUniqueId().toString());
					int death = Stats.sql.getDeaths(p.getUniqueId().toString());
					
					p.sendMessage("§eVoici les statistiques de §6§l"+p.getName());
					p.sendMessage("  §e- Temps passé sur le serveur: §6"+timePlayed);
					p.sendMessage("  §e- Temps passé par jour (en moyenne) sur le serveur: §6"+moyenne);
					p.sendMessage("  §e- Blocs cassés: §6"+broken);
					p.sendMessage("  §e- Blocs posés: §6"+placed);
					p.sendMessage("  §e- Nombre de mort(s): §6"+death);
				}
			}else if(args.length == 1) {
				Player pl = Bukkit.getPlayer(args[0]);
				if(p.isOp()) {
					if(pl == null) {
						p.sendMessage("§cVous ne pouvez pas regarder les stats de ce joueur (hors ligne, ou inconnu)");
					}
					if(core.joinTime.containsKey(pl)) {
						long now = System.currentTimeMillis();
						long first = now - core.joinTime.get(pl) + Long.parseLong(Stats.sql.getSpendTime(pl.getUniqueId().toString()));
						
						String timePlayed = core.timePlayer(first);
						String moyenne = core.moyenne(first);
						int broken = core.broken.get(pl) + Stats.sql.getBrokenBlocks(pl.getUniqueId().toString());
						int placed = core.placed.get(pl) + Stats.sql.getPlacedBlocks(pl.getUniqueId().toString());
						int death = Stats.sql.getDeaths(pl.getUniqueId().toString());
						
						p.sendMessage("§eVoici les statistiques de §6§l"+pl.getName());
						p.sendMessage("  §e- Temps passé sur le serveur: §6"+timePlayed);
						p.sendMessage("  §e- Temps passé par jour (en moyenne) sur le serveur: §6"+moyenne);
						p.sendMessage("  §e- Blocs cassés: §6"+broken);
						p.sendMessage("  §e- Blocs posés: §6"+placed);
						p.sendMessage("  §e- Nombre de mort(s): §6"+death);
					}
				}
			}
		}
		return false;
	}

}
