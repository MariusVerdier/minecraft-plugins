package fr.earthsky.shop.listeners;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.earthsky.shop.Core;

public class clickEventGlobal implements Listener {

	Core core;
	
	public clickEventGlobal(Core c) {
		core = c;
	}
		
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		OfflinePlayer pl = p;
		double money = Core.econ.getBalance(pl);
		if(e.getClickedInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&2&lBoutique &7(&6" + money + "&7)"))){
			e.setCancelled(true);
			switch(e.getCurrentItem().getType()){
			case DIAMOND_CHESTPLATE:
				p.closeInventory();
				core.menuRanks(p);
				break;
			case BARRIER:
				p.closeInventory();
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCette catégorie de la boutique est actuellement indisponible."));
				break;
			default:
				break;
			}
		}
	}

}
