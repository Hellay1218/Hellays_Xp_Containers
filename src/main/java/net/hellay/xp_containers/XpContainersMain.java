package net.hellay.xp_containers;

import net.fabricmc.api.ModInitializer;
import net.hellay.xp_containers.enchantments.EnchantmentEffectComponentTypes;
import net.hellay.xp_containers.enchantments.Enchantments;
import net.hellay.xp_containers.item.ItemGroupRegisterer;
import net.hellay.xp_containers.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XpContainersMain implements ModInitializer {

	public static final String MOD_ID = "xp_containers";

	public static final String MOD_NAME = "Xp Containers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Items.registerFarmingExtensionsItems();
		ItemGroupRegisterer.registerModItemGroups();
		EnchantmentEffectComponentTypes.init();
		Enchantments.init();
		LOGGER.info("Hello fabric from " + MOD_NAME + "!");
	}
}