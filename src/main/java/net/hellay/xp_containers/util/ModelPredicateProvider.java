package net.hellay.xp_containers.util;

import net.hellay.xp_containers.index.XpContainersItems;
import net.hellay.xp_containers.item.XpContainerItemClass;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModelPredicateProvider {
	public static void registerModels() {
		registerXpContainer(XpContainersItems.XP_BOTTLE);
		registerXpContainer(XpContainersItems.XP_JAR);
	}

	private static void registerXpContainer(Item item) {
		ModelPredicateProviderRegistry.register(item, Identifier.of("filled"), ((stack, world, entity, seed) -> XpContainerItemClass.isFilled(stack)));
	}
}
