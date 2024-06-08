package keno.net.complex_fighting;

import keno.net.complex_fighting.events.client.StaminaDisplayHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class CFClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEvents();
    }

    private void registerEvents() {
        HudRenderCallback.EVENT.register(new StaminaDisplayHandler());
    }
}
