package net.hellay.hellays_xp_containers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hellay.hellays_xp_containers.item.HellayItemRegisterer;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class HellayRecipeProvider extends FabricRecipeProvider {

	public HellayRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void generate(RecipeExporter exporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HellayItemRegisterer.XP_SCROLL, 2)
				.pattern("#X#")
				.pattern("#%#")
				.pattern("###")
				.input('#', Items.DIAMOND)
				.input('%', Items.TINTED_GLASS)
				.input('X', HellayItemRegisterer.XP_SCROLL)
				.criterion(hasItem(HellayItemRegisterer.XP_SCROLL), conditionsFromItem(HellayItemRegisterer.XP_SCROLL))
				.offerTo(exporter, Identifier.of(getRecipeName(HellayItemRegisterer.XP_SCROLL)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HellayItemRegisterer.XP_BOTTLE)
				.pattern("%X%")
				.pattern(" # ")
				.input('#', Items.NETHERITE_INGOT)
				.input('%', Items.TINTED_GLASS)
				.input('X', HellayItemRegisterer.XP_SCROLL)
				.criterion(hasItem(HellayItemRegisterer.XP_SCROLL), conditionsFromItem(HellayItemRegisterer.XP_SCROLL))
				.offerTo(exporter, Identifier.of(getRecipeName(HellayItemRegisterer.XP_BOTTLE)));

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, HellayItemRegisterer.XP_JAR)
				.pattern("% %")
				.pattern("%X%")
				.pattern("###")
				.input('#', Items.NETHERITE_INGOT)
				.input('%', Items.TINTED_GLASS)
				.input('X', HellayItemRegisterer.XP_SCROLL)
				.criterion(hasItem(HellayItemRegisterer.XP_SCROLL), conditionsFromItem(HellayItemRegisterer.XP_SCROLL))
				.offerTo(exporter, Identifier.of(getRecipeName(HellayItemRegisterer.XP_JAR)));
	}
}
