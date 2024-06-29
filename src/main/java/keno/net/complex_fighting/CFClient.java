package keno.net.complex_fighting;

import keno.net.complex_fighting.events.client.HudDisplayHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.session.telemetry.WorldLoadedEvent;

@Environment(EnvType.CLIENT)
public class CFClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEvents();
    }

    private void registerEvents() {
        HudRenderCallback.EVENT.register(new HudDisplayHandler());
    }
}
