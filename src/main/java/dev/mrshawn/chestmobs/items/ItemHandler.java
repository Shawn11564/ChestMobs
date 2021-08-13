package dev.mrshawn.chestmobs.items;

import dev.mrshawn.chestmobs.ChestMobs;
import dev.mrshawn.chestmobs.utils.Chat;
import dev.mrshawn.chestmobs.utils.ItemUtils;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public final class ItemHandler {

	private final ChestMobs main = ChestMobs.getInstance();

	public void addMob(ItemStack chest, Entity mob) {
		ItemMeta meta = ItemUtils.getOrCreateItemMeta(chest);
		PersistentDataContainer container = meta.getPersistentDataContainer();
		container.set(ItemUtils.KEY, PersistentDataType.STRING, mob.getType().name());

		if (main.getConfig().getBoolean("items.mob-in-lore")) {
			List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
			if (lore != null) {
				lore.add(Chat.colorize(main.getConfig().getString("items.line")
						.replace("{mob}", mob.getType().toString())));
			}
			meta.setLore(lore);
		}

		chest.setItemMeta(meta);
		mob.remove();
	}

	public void addMob(Block block, EntityType mob) {
		TileState state = (TileState) block.getState();
		state.getPersistentDataContainer().set(ItemUtils.KEY, PersistentDataType.STRING, mob.name());
		state.update();
	}

	public void removeMob(Block block) {
		TileState state = (TileState) block.getState();
		state.getPersistentDataContainer().remove(ItemUtils.KEY);
		state.update();
	}

	public boolean hasMob(ItemStack item) {
		return getDataContainer(item).has(ItemUtils.KEY, PersistentDataType.STRING);
	}

	public boolean hasMob(Block block) {
		return ((TileState) block.getState()).getPersistentDataContainer().has(ItemUtils.KEY, PersistentDataType.STRING);
	}

	public EntityType getMob(ItemStack item) {
		return EntityType.valueOf(getDataContainer(item).get(ItemUtils.KEY, PersistentDataType.STRING).toUpperCase());
	}

	public EntityType getMob(Block block) {
		return EntityType.valueOf(((TileState) block.getState()).getPersistentDataContainer().get(ItemUtils.KEY, PersistentDataType.STRING).toUpperCase());
	}

	public boolean isAllowedToPickup(Entity entity) {
		for (String eType : main.getConfig().getStringList("blacklist")) {
			if (entity.getType().name().equalsIgnoreCase(eType)) {
				return false;
			}
		}
		return true;
	}

	private PersistentDataContainer getDataContainer(ItemStack item) {
		return ItemUtils.getOrCreateItemMeta(item).getPersistentDataContainer();
	}

}
