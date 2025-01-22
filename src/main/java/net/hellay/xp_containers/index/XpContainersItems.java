package net.hellay.xp_containers.index;

import net.hellay.xp_containers.XpContainersMain;
import net.hellay.xp_containers.config.XpContainersConfig;
import net.hellay.xp_containers.item.XpContainerItemClass;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class XpContainersItems {
	public static final Item XP_SCROLL = registerItem("xp_scroll", new Item(new Item.Settings().rarity(Rarity.RARE).registryKey(keyOfItem("xp_scroll"))));
	public static final Item XP_BOTTLE = registerItem("xp_bottle", new XpContainerItemClass(new Item.Settings().registryKey(keyOfItem("xp_bottle")),
			XpContainersConfig.xpBottleMaxXp));
	public static final Item XP_JAR = registerItem("xp_jar", new XpContainerItemClass(new Item.Settings().registryKey(keyOfItem("xp_jar")),
			XpContainersConfig.xpJarMaxXp));

	private static Item registerItem(String name, Item item) {
		return Registry.register(Registries.ITEM, keyOfItem(name), item);
	}

	private static RegistryKey<Item> keyOfItem(String name){
		return RegistryKey.of(RegistryKeys.ITEM , Identifier.of(MOD_ID , name));
	}

	public static void registerXpContainersItems() {
		XpContainersMain.LOGGER.info("Registering items for" + XpContainersMain.MOD_NAME + "...");
	}
}
