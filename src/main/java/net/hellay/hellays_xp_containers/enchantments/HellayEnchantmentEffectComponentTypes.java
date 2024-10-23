package net.hellay.hellays_xp_containers.enchantments;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

import static net.hellay.hellays_xp_containers.HellaysXpContainersMain.MOD_ID;

public class HellayEnchantmentEffectComponentTypes {

	public static final ComponentType<VoluminousEnchantmentEffect> VOLUMINOUS_ENCHANTMENT = register(
			"voluminous",
			builder -> builder.codec(VoluminousEnchantmentEffect.CODEC.codec())
	);

	private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(
				Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE,
				Identifier.of(MOD_ID, id),
				(builderOperator.apply(ComponentType.builder())).build()
		);
	}

	public static void init() {}
}
