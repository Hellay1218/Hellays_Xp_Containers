package net.hellay.xp_containers;

import net.fabricmc.api.ClientModInitializer;
import net.hellay.xp_containers.util.ModelPredicateProvider;

public class XpContainersClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModelPredicateProvider.registerModels();
	}
}