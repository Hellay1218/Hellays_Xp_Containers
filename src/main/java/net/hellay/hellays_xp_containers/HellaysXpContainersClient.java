package net.hellay.hellays_xp_containers;

import net.fabricmc.api.ClientModInitializer;
import net.hellay.hellays_xp_containers.util.HellayModelPredicateProvider;

public class HellaysXpContainersClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		HellayModelPredicateProvider.rgisterModels();
	}
}