package dev.mrshawn.chestmobs.utils;

import dev.mrshawn.chestmobs.ChestMobs;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {

	public static void log(String... messages) {
		for (final String message : messages)
			log(message);
	}

	public static void log(String messages) {
		tell(Bukkit.getConsoleSender(), "[" + ChestMobs.getInstance().getName() + "] " + messages);
	}

	public static void tell(CommandSender toWhom, String... messages) {
		for (final String message : messages)
			tell(toWhom, message);
	}

	public static void tell(CommandSender toWhom, List<String> messages) {
		for (final String message : messages)
			tell(toWhom, message);
	}

	public static void tell(CommandSender toWhom, String message) {
		if (!message.isEmpty())
			toWhom.sendMessage(colorize(message));
	}

	public static void tell(UUID uuid, String message) {
		if (!(Bukkit.getPlayer(uuid) == null))
			if (!message.isEmpty())
				tell(Bukkit.getPlayer(uuid), message);
	}

	public static void tell(UUID uuid, String... messages) {
		for (final String message : messages)
			tell(uuid, message);
	}

	public static void tell(UUID uuid, List<String> messages) {
		for (final String message :messages)
			tell(uuid, message);
	}

	/**
	 * Sends a clickable message to a player that runs a command when clicked.
	 * @param message The clickable message!
	 * @param command The command without the slash to make the user perform.
	 * @param player player to send to.
	 */
	public static void sendClickableCommand(Player player, String message, String command) {
		TextComponent component = new TextComponent(TextComponent.fromLegacyText(colorize(message)));
		component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));

		player.spigot().sendMessage(component);
	}

	public static void broadcast(String message) {
		Bukkit.broadcastMessage(colorize(message));
	}

	public static void broadcast(String... messages) {
		for (final String message : messages) {
			broadcast(message);
		}
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static List<String> colorizeList(List<String> list) {
		List<String> temp = new ArrayList<>();
		for (String s : list)
			temp.add(colorize(s));
		return temp;
	}

	public static String strip(String text) {
		return ChatColor.stripColor(colorize(text));
	}

	public static List<String> strip(List<String> list) {
		List<String> temp = new ArrayList<>();
		for (String s : colorizeList(list)) {
			temp.add(ChatColor.stripColor(s));
		}
		return temp;
	}

	public static int getLength(String text, boolean ignoreColorCodes) {
		return ignoreColorCodes ? strip(text).length() : text.length();
	}

}
