package net.hellay.xp_containers.util;

import net.hellay.xp_containers.init.XpContainerItems;
import net.hellay.xp_containers.items.XpContainerItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateProvider {
	public static void registerModels() {
		registerXpContainer(XpContainerItems.XP_BOTTLE);
		registerXpContainer(XpContainerItems.XP_JAR);
	}

	private static void registerXpContainer(Item item) {
		ModelPredicateProviderRegistry.register(item, Identifier.of("filled"), ((stack, world, entity, seed) -> XpContainerItem.isFilled(stack)));
	}
}
