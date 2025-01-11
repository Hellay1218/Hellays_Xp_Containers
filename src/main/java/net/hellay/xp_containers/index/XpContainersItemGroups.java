package net.hellay.xp_containers.index;

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
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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
					displayContext.lookup().getOptional(RegistryKeys.ENCHANTMENT).ifPresent((registryWrapper) -> {
						entries.add(EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(registryWrapper.getOrThrow(XpContainersEnchantments.VOLUMINOUS_KEY),
								((Enchantment)registryWrapper.getOrThrow(XpContainersEnchantments.VOLUMINOUS_KEY).value()).getMaxLevel())) ,
								ItemGroup.StackVisibility.PARENT_TAB_ONLY);
						addAllLevelEnchantedBooks(entries, registryWrapper, ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
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
	private static void addAllLevelEnchantedBooks(ItemGroup.Entries entries, RegistryWrapper<Enchantment> registryWrapper, ItemGroup.StackVisibility stackVisibility) {
		registryWrapper.streamEntries().flatMap((enchantmentEntry) -> {
			return IntStream.rangeClosed(((Enchantment)registryWrapper.getOrThrow(XpContainersEnchantments.VOLUMINOUS_KEY).value()).getMinLevel(),
					((Enchantment)registryWrapper.getOrThrow(XpContainersEnchantments.VOLUMINOUS_KEY).value()).getMaxLevel()).mapToObj((level)
					-> EnchantmentHelper.getEnchantedBookWith(new EnchantmentLevelEntry(registryWrapper.getOrThrow(XpContainersEnchantments.VOLUMINOUS_KEY), level)));
		}).forEach((stack) -> entries.add(stack, stackVisibility));
	}

	public static void registerModItemGroups() {
		XpContainersMain.LOGGER.info("Registering items groups for " + "Hellay's Xp Containers" + "...");
	}
}

