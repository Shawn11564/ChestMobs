package dev.mrshawn.chestmobs.utils;

import dev.mrshawn.chestmobs.ChestMobs;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	public static final NamespacedKey KEY = new NamespacedKey(ChestMobs.getInstance(), "chestmobs");

	public static ItemMeta getOrCreateItemMeta(ItemStack item) {
		if (item.hasItemMeta()) {
			return item.getItemMeta();
		}
		return Bukkit.getItemFactory().getItemMeta(item.getType());
	}

}
