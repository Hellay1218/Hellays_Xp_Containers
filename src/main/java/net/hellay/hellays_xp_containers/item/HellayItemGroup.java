package net.hellay.hellays_xp_containers.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hellay.hellays_xp_containers.HellaysXpContainersMain;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HellayItemGroup {
	static {
		ItemGroup itemGroup = FabricItemGroup.builder()
				.displayName(Text.translatable("itemgroup.hellays_xp_containers.xp_container_group"))
				.icon(() -> new ItemStack(HellayItemRegisterer.XP_BOTTLE))
				.entries((displayContext, entries) -> {
					entries.add(HellayItemRegisterer.XP_SCROLL);
					entries.add(HellayItemRegisterer.XP_BOTTLE);
					entries.add(HellayItemRegisterer.XP_JAR);
				})
				.build();

		Registry.register(
				Registries.ITEM_GROUP,
				Identifier.of(HellaysXpContainersMain.MOD_ID, "xp_container_group"),
				itemGroup
		);
	}

	public static void registerModItemGroups() {
		HellaysXpContainersMain.LOGGER.info("Registering Mod Items Groups for mod");
	}
}

