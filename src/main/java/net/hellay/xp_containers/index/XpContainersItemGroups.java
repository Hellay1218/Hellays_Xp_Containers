package net.hellay.xp_containers.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hellay.xp_containers.XpContainersMain;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Set;
import java.util.stream.IntStream;

public class XpContainersItemGroups {
	static {
		ItemGroup itemGroup = FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup.xp_containers.xp_container_group"))
				.icon(() -> new ItemStack(XpContainersItems.XP_BOTTLE))
				.entries((displayContext, entries) -> {
					entries.add(XpContainersItems.XP_SCROLL);
					entries.add(XpContainersItems.XP_BOTTLE);
					entries.add(XpContainersItems.XP_JAR);
					entries.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(XpContainersEnchantments.VOLUMINOUS, XpContainersEnchantments.VOLUMINOUS.getMaxLevel())) ,ItemGroup.StackVisibility.PARENT_TAB_ONLY);
					displayContext.lookup().getOptionalWrapper(RegistryKeys.ENCHANTMENT).ifPresent((wrapper) -> {
						addAllLevelEnchantedBooks(entries , ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
					});

				})
				.build();

		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of(XpContainersMain.MOD_ID, "xp_container_group"),
				itemGroup
		);
	}

	//picked this up from the ingredients item group , so useful! (adds every level enchantment)
	private static void addAllLevelEnchantedBooks(ItemGroup.Entries entries, ItemGroup.StackVisibility stackVisibility) {
		IntStream.rangeClosed(XpContainersEnchantments.VOLUMINOUS.getMinLevel(), XpContainersEnchantments.VOLUMINOUS.getMaxLevel()).mapToObj((level) -> {
			return EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(XpContainersEnchantments.VOLUMINOUS, level));
		}).forEach((stack) -> {entries.add(stack, stackVisibility);});
	}

	public static void registerModItemGroups() {
		XpContainersMain.LOGGER.info("Registering items groups for " + "Hellay's Xp Containers" + "...");
	}
}

