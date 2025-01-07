package net.hellay.xp_containers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hellay.xp_containers.XpContainersMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemGroupRegisterer {
	static {
		ItemGroup itemGroup = FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup.xp_containers.xp_container_group"))
				.icon(() -> new ItemStack(Items.XP_BOTTLE))
				.entries((displayContext, entries) -> {
					entries.add(Items.XP_SCROLL);
					entries.add(Items.XP_BOTTLE);
					entries.add(Items.XP_JAR);
				})
				.build();

		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of(XpContainersMain.MOD_ID, "xp_container_group"),
				itemGroup
		);
	}

	public static void registerModItemGroups() {
		XpContainersMain.LOGGER.info("Registering items groups for " + "Hellay's Xp Containers" + "...");
	}
}

