package net.hellay.hellays_xp_containers;

import net.fabricmc.api.ModInitializer;
import net.hellay.hellays_xp_containers.enchantments.HellayEnchantmentEffectComponentTypes;
import net.hellay.hellays_xp_containers.enchantments.HellayEnchantments;
import net.hellay.hellays_xp_containers.item.HellayItemGroup;
import net.hellay.hellays_xp_containers.item.HellayItemRegisterer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellaysXpContainersMain implements ModInitializer {

	public static final String MOD_ID = "hellays_xp_containers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		HellayItemRegisterer.registerFarmingExtensionsItems();
		HellayItemGroup.registerModItemGroups();
		HellayEnchantmentEffectComponentTypes.init();
		HellayEnchantments.init();
		LOGGER.info("Hello fabric from Hellay's Xp Containers!");
	}
}