package net.hellay.xp_containers.item;

import net.hellay.xp_containers.config.XpContainersConfig;
import net.hellay.xp_containers.index.XpContainersEnchantments;
import net.hellay.xp_containers.util.XpState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class XpContainerItemClass extends Item {

	private static final SoundEvent ERROR_SOUND = SoundEvents.BLOCK_NOTE_BLOCK_BASS.value();
	private static final SoundEvent SUCCESS_SOUND = SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;

	private static boolean UsePoints = XpContainersConfig.xpStateEnum == XpContainersConfig.XpStateEnum.POINTS;

	private final int INITIAL_MAX_XP_POINTS;

	/**
	 * To translate levels to points use {@link net.hellay.xp_containers.util.XpState#levelToPoints(float)}
	 **/
	public XpContainerItemClass(Settings settings, int maxXpPoints) {
		super(settings.maxCount(1).rarity(Rarity.RARE).fireproof());
		INITIAL_MAX_XP_POINTS = XpState.levelToPoints(maxXpPoints);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		final int oldContainedXp = getContainedXp(stack);

		if (!user.isSneaking()) {
			int userXp = XpState.levelAndProgressToPoints(user.experienceLevel, user.experienceProgress);
			int newContainedXp = Math.min(oldContainedXp + userXp, getMaxXpPoints(stack));
			if(!UsePoints){
				userXp = user.experienceLevel;
				newContainedXp = Math.min(oldContainedXp + userXp, (int)XpState.pointsToLevelsDecimal(getMaxXpPoints(stack)));
			}
			int addedXp = newContainedXp - oldContainedXp;

			if (addedXp <= 0) {
				playSound(world, user, ERROR_SOUND);
				return TypedActionResult.fail(stack);
			}
			playSound(world, user, SUCCESS_SOUND);
			createParticles(world, user);
			setContainedXp(stack, newContainedXp);
			if(!UsePoints){
				user.experienceLevel -= addedXp;
				return TypedActionResult.success(stack);
			}
			user.addExperience(-addedXp);

		} else {
			if (oldContainedXp <= 0) {
				playSound(world, user, ERROR_SOUND);
				return TypedActionResult.fail(stack);
			}
			playSound(world, user, SUCCESS_SOUND);
			createParticles(world, user);
			setContainedXp(stack, 0);
			if(!UsePoints){
				user.experienceLevel += oldContainedXp;
				return TypedActionResult.success(stack);
			}
			user.addExperience(oldContainedXp);
		}

		return TypedActionResult.fail(stack);
	}

	private void createParticles(World world, PlayerEntity user) {
		for (int i = 0; i <= 60; i++) {

			float haX = user.getRandom().nextFloat() * (user.getRandom().nextBoolean() ? -0.75f : 0.75f);
			float haZ = user.getRandom().nextFloat() * (user.getRandom().nextBoolean() ? -0.75f : 0.75f);
			world.addParticle(ParticleTypes.COMPOSTER, user.getX() + haX, user.getY() + (i / 30d), user.getZ() + haZ, 0.01, 100, 100);


			//particle experiments

			//float X = (float) Math.sin(i*6);
			//float Z = (float) Math.cos(i*6);

			//float Y = 0.4f;
			//if(20 < i && i <= 40){Y = 1f;} else if (40 < i) {Y = 1.6f;}
			//world.addParticle(i%2 == 0 ? ParticleTypes.WAX_OFF : ParticleTypes.WAX_ON,
			//		user.getX() + X/1.4, user.getY() + Y, user.getZ() + Z/1.4, 2 *(Math.sin((i-1) * 6) - X) , 0, 2 *(Math.cos((i-1) * 6) - Z));

			//float Y = 0.2f;
			//if(20 < i && i <= 40){Y = 0.8f;} else if (40 < i) {Y = 1.4f;}
			//world.addParticle(ParticleTypes.HAPPY_VILLAGER,
			//		user.getX() + X/1.4, user.getY() + Y, user.getZ() + Z/1.4, 0 , 0, 0);


			//world.addParticle(ParticleTypes.END_ROD, true , user.getX() + X/1.4, user.getY() + (i/45d) + 0.2, user.getZ() + Z/1.4, ((Math.sin((i - 360) * 6))- X)/64 , 0, ((Math.cos((i - 360) * 6))- Z)/64);

			// uncomment this one for a cool effect ;D
			//float X = (float) Math.sin(i*18);
			//float Z = (float) Math.cos(i*18);
			//float Y = 0.4f;
			//if(20 < i && i <= 40){Y = 1f;} else if (40 < i) {Y = 1.4f;}
			//world.addParticle(i%2 == 0 ? ParticleTypes.WAX_OFF : ParticleTypes.WAX_ON,
			//		user.getX() + X/1.4, user.getY() + Y, user.getZ() + Z/1.4, 100 *(Math.sin((i-1) * 18)) , 0, 100 *(Math.cos((i-1) * 18)));

			//here's another one :D
			//world.addParticle(ParticleTypes.END_ROD, user.getX() + X/1.4, user.getY() + Y, user.getZ() + Z/1.4,
			//		 (float) Math.sin((i-1)*18) , 0, (float) Math.cos((i-1)*18));
		}
		System.out.println("finish");
	}

	private void playSound(World world, PlayerEntity user, SoundEvent sound) {
		world.playSound(user, user.getX(), user.getY(), user.getZ(), sound, SoundCategory.NEUTRAL, 1, 1);
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
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		if (stack.hasGlint()) {
			var toolTipPart1 = Text.translatable("itemtooltip.xp_containers.xp_container.part1").getString();
			var toolTipFull = Text.translatable("itemtooltip.xp_containers.xp_container.part2").getString();

			DecimalFormat df = new DecimalFormat("#.##");
			df.setMinimumFractionDigits(0);
			df.setMaximumFractionDigits(2);

			String stored_level = toolTipPart1.formatted(
					df.format(UsePoints ? XpState.pointsToLevelsDecimal(getContainedXp(stack)) : getContainedXp(stack)),
					df.format(XpState.pointsToLevelsDecimal(getMaxXpPoints(stack))),
					getContainedXp(stack) == getMaxXpPoints(stack) ? toolTipFull : ""
			).trim();
			tooltip.add(Text.literal(stored_level).formatted(Formatting.GREEN));
		}
	}

	public int getMaxXpPoints(ItemStack stack) {
		var p = EnchantmentHelper.getLevel(XpContainersEnchantments.VOLUMINOUS, stack);
		if (p == 0)
			return INITIAL_MAX_XP_POINTS;

		var initialMaxLevel = XpState.pointsToLevelsDecimal(INITIAL_MAX_XP_POINTS);
		return XpState.levelToPoints(initialMaxLevel + initialMaxLevel / 4 * p);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	public static float isFilled(ItemStack stack) {
		if (stack.getItem() instanceof XpContainerItemClass)
			return getContainedXp(stack) > 0 ? 1f : 0f;
		return 0f;
	}

	public static void setContainedXp(ItemStack stack, int xp) {
		stack.getOrCreateNbt().putInt("xp" , xp);
	}

	public static int getContainedXp(ItemStack stack) {
		return stack.getOrCreateNbt().getInt("xp");
	}
}
