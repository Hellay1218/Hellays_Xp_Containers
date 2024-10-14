package net.hellay.hellays_xp_containers.util;

import net.hellay.hellays_xp_containers.item.HellayItemRegisterer;
import net.hellay.hellays_xp_containers.item.custom_items.XpContainerItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class HellayModelPredicateProvider {
    public static void registerModels(){
        registerXpContainer(HellayItemRegisterer.XP_BOTTLE);
        registerXpContainer(HellayItemRegisterer.XP_JAR);
    }
    private static void registerXpContainer(Item item){
        ModelPredicateProviderRegistry.register(item,Identifier.of("filled"),((stack, world, entity, seed) -> XpContainerItem.IsFilled(stack)));
    }
}
