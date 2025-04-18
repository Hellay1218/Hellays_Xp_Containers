package net.hellay.xp_containers;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.hellay.xp_containers.config.XpContainersConfig;
import net.hellay.xp_containers.init.XpContainerEnchantmentEffectComponentTypes;
import net.hellay.xp_containers.init.XpContainerEnchantments;
import net.hellay.xp_containers.init.XpContainerItemGroups;
import net.hellay.xp_containers.init.XpContainerItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XpContainersMain implements ModInitializer {

	public static final String MOD_ID = "xp_containers";

	public static final String MOD_NAME = "Xp Containers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MidnightConfig.init(MOD_ID , XpContainersConfig.class);
		XpContainerItems.init();
		XpContainerItemGroups.init();
		XpContainerEnchantmentEffectComponentTypes.init();
		XpContainerEnchantments.init();
		LOGGER.info("Hello fabric from " + MOD_NAME + "!");
	}
}