package net.hellay.xp_containers.enchantment;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

import static net.hellay.xp_containers.XpContainersMain.MOD_ID;

public class EnchantmentEffectComponentTypes {

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
