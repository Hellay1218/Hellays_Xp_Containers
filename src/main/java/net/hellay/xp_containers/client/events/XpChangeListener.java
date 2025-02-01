package net.hellay.xp_containers.client.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class XpChangeListener implements ClientTickEvents.EndTick {

	private static int lastXp = -1;

	@Override
	public void onEndTick(MinecraftClient client) {
		if (client.player == null)
			return;

		int currentXp = client.player.totalExperience;
		if (currentXp != lastXp) {
			lastXp = currentXp;

			if (client.currentScreen != null)
				client.currentScreen.init(client, client.currentScreen.width, client.currentScreen.height);
		}
	}
}
