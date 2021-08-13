package dev.mrshawn.chestmobs.listeners;

import dev.mrshawn.chestmobs.ChestMobs;
import dev.mrshawn.chestmobs.items.ItemHandler;
import dev.mrshawn.chestmobs.utils.Chat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public final class ChestOpenListener implements Listener {

	private final ChestMobs main;
	private final ItemHandler itemHandler;

	public ChestOpenListener(ChestMobs main, ItemHandler itemHandler) {
		this.main = main;
		this.itemHandler = itemHandler;
	}

	@EventHandler
	public void onChestOpen(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHEST) {
			if (itemHandler.hasMob(e.getClickedBlock())) {
				FileConfiguration config = main.getConfig();
				String path = "spawning.offset";
				EntityType mobType = itemHandler.getMob(e.getClickedBlock());
				Location location = e.getClickedBlock().getLocation();
				World world = location.getWorld();
				world.spawnEntity(location.add(
						config.getDouble(path + ".x"),
						config.getDouble(path + ".y"),
						config.getDouble(path + ".z")
				), mobType);
				Chat.tell(e.getPlayer(), config.getString("messages.mob-spawned")
						.replace("{mob}", mobType.name()));

				if (main.getConfig().getBoolean("blocks.one-time-use")) {
					itemHandler.removeMob(e.getClickedBlock());
				}

			}
		}
	}

}
