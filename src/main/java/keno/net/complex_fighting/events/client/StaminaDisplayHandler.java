package keno.net.complex_fighting.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;

//TODO Create HUD Textures and implement stamina bar
@Environment(EnvType.CLIENT)
public class StaminaDisplayHandler implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int color = 0xffffffff;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        drawContext.fill(100, 10, 330, 20, 0, color);
    }
}
