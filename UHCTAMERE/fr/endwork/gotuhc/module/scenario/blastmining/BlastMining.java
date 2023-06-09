package fr.endwork.gotuhc.module.scenario.blastmining;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Scenario;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@Module(lifeCycle = LifeCycle.GAME)
@Scenario(
    name = "Blast Mining",
    desc = "When a player mines ore, there is a small chance of a creeper or TNT appearing."
)
public class BlastMining implements IModule, Listener {

  public static final List<Material> ORES = Arrays.asList(
      Material.COAL_ORE,
      Material.DIAMOND_ORE,
      Material.EMERALD_ORE,
      Material.GOLD_ORE,
      Material.IRON_ORE,
      Material.LAPIS_ORE,
      Material.NETHER_QUARTZ_ORE,
      Material.REDSTONE_ORE
      );

  @Override
  public void onEnable() {
    GameCore.registerEvents(this);
  }

  @Override
  public void onDisable() {
    HandlerList.unregisterAll(this);
  }

  /**
   * Spawns a creeper or primed TNT when a player breaks an ore block, by chance.
   *
   * @param event The event
   */
  @EventHandler(ignoreCancelled = true)
  public void onBlockBreak(BlockBreakEvent event) {
    Block block = event.getBlock();
    if (ORES.contains(block.getType())) {
      double chance = Math.random();
      Location location = block.getLocation().add(0.5, 0.5, 0.5);
      if (0.05 > chance) {
        TNTPrimed tnt = location.getWorld().spawn(location, TNTPrimed.class);
        tnt.setFuseTicks(80);
      } else if (0.1 > chance) {
        Creeper creeper = location.getWorld().spawn(location, Creeper.class);
        creeper.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 2));
      }
    }
  }

}
