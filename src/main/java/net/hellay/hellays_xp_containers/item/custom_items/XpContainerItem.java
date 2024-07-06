package net.hellay.hellays_xp_containers.item.custom_items;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
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


    public XpContainerItem(Settings settings , int max_xp) {
        super(settings.maxCount(1).rarity(Rarity.EPIC).fireproof());
        max_xp_level = max_xp;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.experienceLevel != 0 && GetContainedXp(stack) == 0) {
            for (int i = 0; i <= 60; i++) {
                float haX = Random.create().nextFloat() * (Random.create().nextBoolean() ? -0.75f : 0.75f);
                float haZ = Random.create().nextFloat() * (Random.create().nextBoolean() ? -0.75f : 0.75f);
                world.addParticle(ParticleTypes.COMPOSTER, user.getX() + haX, user.getY() + (i / 30d), user.getZ() + haZ, 0.01, 100, 100);
            }
            world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, 1);
            if (user.experienceLevel <= max_xp_level) {
                SetContainedXp(stack , user.experienceLevel);
                user.experienceLevel = 0;
            } else {
                SetContainedXp(stack , max_xp_level);
                user.experienceLevel -= this.max_xp_level;
            }

            user.getItemCooldownManager().set(this, 15);
        }
        else if(GetContainedXp(stack) != 0){
            for (int i = 0; i <= 60; i++) {
                float haX = Random.create().nextFloat() * (Random.create().nextBoolean() ? -0.75f : 0.75f);
                float haZ = Random.create().nextFloat() * (Random.create().nextBoolean() ? -0.75f : 0.75f);
                world.addParticle(ParticleTypes.COMPOSTER, user.getX() + haX, user.getY() + (i / 30d), user.getZ() + haZ, 0.01, 100, 100);
            }
            
            world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), SoundCategory.NEUTRAL, 1, 1);
            world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, 1);
            user.experienceLevel += GetContainedXp(stack);
            RemoveContainedXp(stack);
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return GetContainedXp(stack) != 0;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)  {
        if (stack.hasGlint()){
            String stored_level = "Contains " + GetContainedXp(stack) + " Experience Levels" + (GetContainedXp(stack) == this.max_xp_level ? " (Full)" : "");
            tooltip.add(Text.literal(stored_level).formatted(Formatting.GREEN));
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public static float IsFull(ItemStack stack){
        return stack.hasGlint() ? 1f : 0f;
    }

    public static void SetContainedXp(ItemStack stack , int xp){
        stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> currentNbt.putInt("xp", xp)));
    }

    public static int GetContainedXp(ItemStack stack){
        if (stack.get(DataComponentTypes.CUSTOM_DATA) != null){
            if (stack.get(DataComponentTypes.CUSTOM_DATA).copyNbt().contains("xp")){
                return stack.get((DataComponentTypes.CUSTOM_DATA)).copyNbt().getInt("xp");
            }
        }
        return 0;
    }

    public static void RemoveContainedXp(ItemStack stack){
        stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp -> comp.apply(currentNbt -> currentNbt.putInt("xp", 0)));
    }

}
