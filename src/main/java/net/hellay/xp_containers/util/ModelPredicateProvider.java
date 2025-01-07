package net.hellay.xp_containers.util;

import net.hellay.xp_containers.item.Items;
import net.hellay.xp_containers.item.XpContainerItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateProvider {
	public static void registerModels() {
		registerXpContainer(Items.XP_BOTTLE);
		registerXpContainer(Items.XP_JAR);
	}

	private static void registerXpContainer(Item item) {
		ModelPredicateProviderRegistry.register(item, Identifier.of("filled"), ((stack, world, entity, seed) -> XpContainerItem.isFilled(stack)));
	}
}
