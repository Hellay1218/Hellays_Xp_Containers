package net.hellay.xp_containers.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.hellay.xp_containers.client.events.XpChangeListener;
import net.hellay.xp_containers.util.ModelPredicateProvider;

public class XpContainersClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModelPredicateProvider.registerModels();
		ClientTickEvents.END_CLIENT_TICK.register(client -> new XpChangeListener());
	}
}