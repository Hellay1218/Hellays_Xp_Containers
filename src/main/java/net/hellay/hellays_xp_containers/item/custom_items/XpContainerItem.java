package net.hellay.hellays_xp_containers.item.custom_items;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class XpContainerItem extends Item {

	int max_xp_level;

	public XpContainerItem(Settings settings, int max_xp) {
		super(settings.maxCount(1).rarity(Rarity.RARE).fireproof());
		max_xp_level = max_xp;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);

		if (user.experienceLevel != 0 && getContainedXp(stack) <= 0) {
			createParticles(world, user);
			world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, 1);

			setContainedXp(stack, Math.min(user.experienceLevel, max_xp_level));
			user.experienceLevel -= getContainedXp(stack);

			user.getItemCooldownManager().set(this, 15);
		} else if (getContainedXp(stack) >= 0) {
			createParticles(world, user);
			world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), SoundCategory.NEUTRAL, 1, 1);
			world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, 1);

			user.experienceLevel += getContainedXp(stack);
			removeContainedXp(stack);
		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}

	private void createParticles(World world, PlayerEntity user) {
        for (int i = 0; i <= 60; i++) {
			float haX = user.getRandom().nextFloat() * (user.getRandom().nextBoolean() ? -0.75f : 0.75f);
			float haZ = user.getRandom().nextFloat() * (user.getRandom().nextBoolean() ? -0.75f : 0.75f);
			world.addParticle(ParticleTypes.COMPOSTER, user.getX() + haX, user.getY() + (i / 30d), user.getZ() + haZ, 0.01, 100, 100);
		}
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		return stack;
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		return getContainedXp(stack) > 0;
	}

	@Override
	public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		if (stack.hasGlint()) {
			String stored_level = "Contains " + getContainedXp(stack) + " Experience Levels" + (getContainedXp(stack) == this.max_xp_level ? " (Full)" : "");
			tooltip.add(Text.literal(stored_level).formatted(Formatting.GREEN));
		}
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	public static float isFilled(ItemStack stack) {
		if (stack.getItem() instanceof XpContainerItem)
			return getContainedXp(stack) > 0 ? 1f : 0f;
		return 0f;
	}

	public static void setContainedXp(ItemStack stack, int xp) {
		stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> currentNbt.putInt("xp", xp)));
	}

	public static int getContainedXp(ItemStack stack) {
		if (stack.get(DataComponentTypes.CUSTOM_DATA) == null)
			return 0;

		NbtCompound nbtData = stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt();
		return nbtData.contains("xp") ? nbtData.getInt("xp") : 0;
	}

	public static void removeContainedXp(ItemStack stack) {
		stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> currentNbt.putInt("xp", 0)));
	}
}
