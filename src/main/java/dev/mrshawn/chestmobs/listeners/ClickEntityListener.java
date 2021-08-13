package dev.mrshawn.chestmobs.listeners;

import dev.mrshawn.chestmobs.ChestMobs;
import dev.mrshawn.chestmobs.items.ItemHandler;
import dev.mrshawn.chestmobs.utils.Chat;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public final class ClickEntityListener implements Listener {

	private final ChestMobs main;
	private final FileConfiguration config;
	private final ItemHandler itemHandler;

	public ClickEntityListener(ChestMobs main, ItemHandler itemHandler) {
		this.main = main;
		this.config = main.getConfig();
		this.itemHandler = itemHandler;
	}

	@EventHandler
	public void onRightClick(PlayerInteractAtEntityEvent e) {
		if (e.getRightClicked() instanceof Creature) {
			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			if (item.getType() == Material.CHEST) {
				if (!itemHandler.isAllowedToPickup(e.getRightClicked())) {
					Chat.tell(e.getPlayer(), main.getConfig().getString("messages.cant-pickup")
							.replace("{mob}", e.getRightClicked().getType().name()));
					return;
				}
				if (itemHandler.hasMob(item)) {
					Chat.tell(e.getPlayer(), config.getString("messages.already-has-mob")
							.replace("{mob}", itemHandler.getMob(item).toString()));
				} else {
					itemHandler.addMob(item, e.getRightClicked());
				}
			}
		}
	}

}
