package keno.net.complex_fighting.events.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;

//TODO Create HUD Textures and implement stamina bar
@Environment(EnvType.CLIENT)
public class StaminaDisplayHandler implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
    }
}
