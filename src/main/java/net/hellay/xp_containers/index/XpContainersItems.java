package net.hellay.xp_containers.index;

import net.hellay.xp_containers.XpContainersMain;
import net.hellay.xp_containers.config.XpContainersConfig;
import net.hellay.xp_containers.item.XpContainerItemClass;
import net.hellay.xp_containers.util.XpState;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class XpContainersItems {
	public static final RegistryKey<Item> XP_SCROLL_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "xp_scroll"));
	public static final Item XP_SCROLL = registerItem(XP_SCROLL_KEY, new Item(new Item.Settings().rarity(Rarity.RARE).registryKey(XP_SCROLL_KEY)));

	public static final RegistryKey<Item> XP_BOTTLE_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "xp_bottle"));
	public static final Item XP_BOTTLE = registerItem(XP_BOTTLE_KEY, new XpContainerItemClass(new Item.Settings().registryKey(XP_BOTTLE_KEY),
			XpContainersConfig.xpBottleMaxXp));

	public static final RegistryKey<Item> XP_JAR_KEY = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "xp_jar"));
	public static final Item XP_JAR = registerItem(XP_JAR_KEY, new XpContainerItemClass(new Item.Settings().registryKey(XP_JAR_KEY),
			XpContainersConfig.xpJarMaxXp));

	private static Item registerItem(RegistryKey<Item> key, Item item) {
		return Registry.register(Registries.ITEM, key, item);
	}

	public static void registerFarmingExtensionsItems() {
		XpContainersMain.LOGGER.info("Registering items for" + XpContainersMain.MOD_NAME + "...");
	}
}
