package net.hellay.hellays_xp_containers.item;

import net.hellay.hellays_xp_containers.HellaysXpContainersMain;
import net.hellay.hellays_xp_containers.item.custom_items.XpContainerItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class HellayItemRegisterer {
    private static final String MOD_ID = HellaysXpContainersMain.MOD_ID;

    public static final Item XP_CONTAINER_TEMPLATE = registerItem("xp_container_template" , new Item(new Item.Settings()));
    public static final Item XP_BOTTLE = registerItem("xp_bottle" , new XpContainerItem(new Item.Settings()  , 16));
    public static final Item XP_JAR = registerItem("xp_jar" , new XpContainerItem(new Item.Settings().recipeRemainder(XP_BOTTLE) , 64));

    private static Item registerItem(String name ,Item item){
        return Registry.register(Registries.ITEM , Identifier.of(MOD_ID , name), item);
    }

    public static void registerFarmingExtensionsItems(){
        HellaysXpContainersMain.LOGGER.info("Registering Items For Hellay's Xp Containers ...");
    }

}
