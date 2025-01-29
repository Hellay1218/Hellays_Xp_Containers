package net.hellay.xp_containers.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hellay.xp_containers.XpContainersMain;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class XpContainerItemGroups {
	static {
		ItemGroup itemGroup = FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup.xp_containers.xp_container_group"))
				.icon(() -> new ItemStack(XpContainerItems.XP_BOTTLE))
				.entries((displayContext, entries) -> {
					entries.add(XpContainerItems.XP_SCROLL);
					entries.add(XpContainerItems.XP_BOTTLE);
					entries.add(XpContainerItems.XP_JAR);
					displayContext.lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent((registryWrapper) -> {
						RegistryEntry.Reference<Enchantment> enchantmentReference = registryWrapper.getOrThrow(XpContainerEnchantments.VOLUMINOUS_KEY);
						ItemStack enchantedBook = EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(enchantmentReference, enchantmentReference.value().getMaxLevel()));
						entries.add(enchantedBook, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
					});
				})
				.build();

		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of(XpContainersMain.MOD_ID, "xp_container_group"),
				itemGroup
		);
	}

	public static void init() {}
}
