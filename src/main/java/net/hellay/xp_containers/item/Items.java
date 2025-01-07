package net.hellay.xp_containers.item;

import net.hellay.xp_containers.XpContainersMain;
import net.hellay.xp_containers.util.XpState;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class Items {
	public static final Item XP_SCROLL = registerItem("xp_scroll", new Item(new Item.Settings().rarity(Rarity.RARE)));
	public static final Item XP_BOTTLE = registerItem("xp_bottle", new XpContainerItem(new Item.Settings(), XpState.levelToPoints(16)));
	public static final Item XP_JAR = registerItem("xp_jar", new XpContainerItem(new Item.Settings(), XpState.levelToPoints(64)));

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, Identifier.of(MOD_ID, name), item);
	}

	public static void registerFarmingExtensionsItems() {
		XpContainersMain.LOGGER.info("Registering items for" + XpContainersMain.MOD_NAME + "...");
	}
}
