package net.hellay.xp_containers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output);
	}


	@Override
	public String getName() {
		return "";
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> exporter) {

	}
}
