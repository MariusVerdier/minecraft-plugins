package fr.endwork.gotuhc.module.lobby;

import fr.endwork.gotuhc.GameCore;
import fr.endwork.gotuhc.module.IModule;
import fr.endwork.gotuhc.module.LifeCycle;
import fr.endwork.gotuhc.module.Module;
import fr.endwork.gotuhc.module.Setting;
import fr.endwork.gotuhc.util.Numbers;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;

@Module(lifeCycle = LifeCycle.SERVER, enableOnStart = true)
public class LobbyModule implements IModule, Listener {

    private World world;

    @Setting("Spawn Location") private Vector spawn = new Vector(0, 64, 0);
    @Setting("Spawn Radius") private float radius = 1;

    @Override
    public void onEnable() {
        world = Bukkit.createWorld(new WorldCreator(GameCore.WORLD_DIR_PREFIX + "lobby"));
        // Read lobby yml if it exists
        File lobbyFile = new File(GameCore.WORLD_DIR_PREFIX + "lobby/lobby.yml");
        if (lobbyFile.exists()) {
            FileConfiguration lobbyConfig = YamlConfiguration.loadConfiguration(lobbyFile);
            ConfigurationSection spawn = lobbyConfig.getConfigurationSection("spawn");
            if (spawn != null) {
                double x = spawn.getDouble("x", 0);
                double y = spawn.getDouble("y", 64);
                double z = spawn.getDouble("z", 0);
                double r = spawn.getDouble("r", 1);
                this.spawn = new Vector(x, y, z);
                radius = (float) r;
            }
        }
        GameCore.registerEvents(this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }

    private Location getSpawnLocation() {
        Vector offset = new Vector(Numbers.randomFloat(-1 * radius , radius), 0, Numbers.randomFloat(-1 * radius, radius));
        return new Location(world, spawn.getX() + offset.getX(), spawn.getY(), spawn.getZ() + offset.getZ());
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        // TODO: verify they aren't rejoining the match
        Bukkit.getScheduler().runTask(GameCore.getInstance(), () -> event.getPlayer().teleport(getSpawnLocation()));
    }

    // Prevent world interaction

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        if (event.getBlock().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        if (event.getBlock().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onBucketFill(final PlayerBucketFillEvent event) {
        if (event.getBlockClicked().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onBucketEmpty(final PlayerBucketEmptyEvent event) {
        if (event.getBlockClicked().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        if (event.getPlayer().getLocation().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onPlayerInteractEntity(final PlayerInteractAtEntityEvent event) {
        if (event.getPlayer().getLocation().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }

    /**
     * Prevents lobby players from interacting in the world.
     *
     * @param event The event
     */
    @EventHandler
    public void onEntitySpawn(final EntitySpawnEvent event) {
        if (event.getLocation().getWorld().equals(world)) {
            event.setCancelled(true);
        }
    }
}
