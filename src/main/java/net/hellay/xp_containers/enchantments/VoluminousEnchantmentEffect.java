package net.hellay.xp_containers.enchantments;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;

public record VoluminousEnchantmentEffect(
		EnchantmentLevelBasedValue amount
) {
	public static final MapCodec<VoluminousEnchantmentEffect> CODEC = RecordCodecBuilder
			.mapCodec(instace -> instace.group(EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(VoluminousEnchantmentEffect::amount))
					.apply(instace, VoluminousEnchantmentEffect::new));
}
