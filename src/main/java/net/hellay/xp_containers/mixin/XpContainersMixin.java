package net.hellay.xp_containers.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MinecraftServer.class)
public class XpContainersMixin {
	//@Inject(at = @At("HEAD"), method = "loadWorld")
	//private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()
	//}
}