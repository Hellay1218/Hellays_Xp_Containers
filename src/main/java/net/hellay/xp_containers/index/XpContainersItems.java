package net.hellay.xp_containers.index;

import net.hellay.xp_containers.XpContainersMain;
import net.hellay.xp_containers.config.XpContainersConfig;
import net.hellay.xp_containers.item.XpContainerItemClass;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class XpContainersItems {
	public static final Item XP_SCROLL = registerItem("xp_scroll", new Item(new Item.Settings().rarity(Rarity.RARE)));
	public static final Item XP_BOTTLE = registerItem("xp_bottle", new XpContainerItemClass(new Item.Settings(),
			XpContainersConfig.xpBottleMaxXp));
	public static final Item XP_JAR = registerItem("xp_jar", new XpContainerItemClass(new Item.Settings(),
			XpContainersConfig.xpJarMaxXp));

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(MOD_ID , name), item);
	}

	public static void registerFarmingExtensionsItems() {
		XpContainersMain.LOGGER.info("Registering items for" + XpContainersMain.MOD_NAME + "...");
	}
}
