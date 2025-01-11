package net.hellay.xp_containers.index;

import net.hellay.xp_containers.XpContainersMain;
import net.hellay.xp_containers.enchantment.VoluminousEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class XpContainersEnchantments {
	public static final Enchantment VOLUMINOUS = registerEnchantment("voluminous" , new VoluminousEnchantment());

	private static Enchantment registerEnchantment(String name , Enchantment enchantment) {
		return Registry.register(Registries.ENCHANTMENT , new Identifier(MOD_ID , name) , enchantment);
	}

	public static void init() {
		XpContainersMain.LOGGER.info("Registering enchantments for" + XpContainersMain.MOD_NAME + "...");
	}
}
