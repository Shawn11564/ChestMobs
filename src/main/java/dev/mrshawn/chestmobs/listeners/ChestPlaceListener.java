package dev.mrshawn.chestmobs.listeners;

import dev.mrshawn.chestmobs.ChestMobs;
import dev.mrshawn.chestmobs.items.ItemHandler;
import dev.mrshawn.chestmobs.utils.Chat;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public final class ChestPlaceListener implements Listener {

	private final ChestMobs main;
	private final ItemHandler itemHandler;

	public ChestPlaceListener(ChestMobs main, ItemHandler itemHandler) {
		this.main = main;
		this.itemHandler = itemHandler;
	}

	@EventHandler
	public void onChestPlace(BlockPlaceEvent e) {
		if (e.getBlockPlaced().getType() == Material.CHEST && e.getItemInHand().getType() == Material.CHEST) {
			ItemStack item = e.getItemInHand();
			if (itemHandler.hasMob(item)) {
				EntityType mob = itemHandler.getMob(item);
				itemHandler.addMob(e.getBlockPlaced(), mob);
				Chat.tell(e.getPlayer(), main.getConfig().getString("messages.chest-placed")
						.replace("{mob}", mob.toString()));
			}
		}
	}

}
