package dev.mrshawn.chestmobs;

import dev.mrshawn.chestmobs.items.ItemHandler;
import dev.mrshawn.chestmobs.listeners.ChestOpenListener;
import dev.mrshawn.chestmobs.listeners.ChestPlaceListener;
import dev.mrshawn.chestmobs.listeners.ClickEntityListener;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChestMobs extends JavaPlugin {

	@Getter
	private static ChestMobs instance;

	@Getter
	private ItemHandler itemHandler;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		itemHandler = new ItemHandler();

		registerListeners();
	}

	private void registerListeners() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new ClickEntityListener(this, itemHandler), this);
		pm.registerEvents(new ChestPlaceListener(this, itemHandler), this);
		pm.registerEvents(new ChestOpenListener(this, itemHandler), this);
	}
}
