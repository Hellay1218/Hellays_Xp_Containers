package net.hellay.xp_containers.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class Enchantments {
	public static final RegistryKey<Enchantment> VOLUMINOUS_KEY = registerEnchantment("voluminous");

	private static RegistryKey<Enchantment> registerEnchantment(String name) {
		return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MOD_ID, name));
	}

	public static void init() {
	}
}
