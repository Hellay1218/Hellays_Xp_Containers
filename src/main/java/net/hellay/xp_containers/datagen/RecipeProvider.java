package net.hellay.xp_containers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hellay.xp_containers.init.XpContainerItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
		return new RecipeGenerator(registryLookup, exporter) {
			@Override
			public void generate() {
				ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.MISC, XpContainerItems.XP_SCROLL, 2)
						.pattern("#X#")
						.pattern("#%#")
						.pattern("###")
						.input('#', Items.DIAMOND)
						.input('%', Items.TINTED_GLASS)
						.input('X', XpContainerItems.XP_SCROLL)
						.criterion(hasItem(XpContainerItems.XP_SCROLL), conditionsFromItem(XpContainerItems.XP_SCROLL))
						.offerTo(exporter, getRecipeName(XpContainerItems.XP_SCROLL));

				ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.MISC, XpContainerItems.XP_BOTTLE)
						.pattern("%X%")
						.pattern(" # ")
						.input('#', Items.NETHERITE_INGOT)
						.input('%', Items.TINTED_GLASS)
						.input('X', XpContainerItems.XP_SCROLL)
						.criterion(hasItem(XpContainerItems.XP_SCROLL), conditionsFromItem(XpContainerItems.XP_SCROLL))
						.offerTo(exporter, getRecipeName(XpContainerItems.XP_BOTTLE));

				ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.MISC, XpContainerItems.XP_JAR)
						.pattern("% %")
						.pattern("%X%")
						.pattern("###")
						.input('#', Items.NETHERITE_INGOT)
						.input('%', Items.TINTED_GLASS)
						.input('X', XpContainerItems.XP_SCROLL)
						.criterion(hasItem(XpContainerItems.XP_SCROLL), conditionsFromItem(XpContainerItems.XP_SCROLL))
						.offerTo(exporter, getRecipeName(XpContainerItems.XP_JAR));
			}
		};
	}

	@Override
	public String getName() {
		return "";
	}
}
