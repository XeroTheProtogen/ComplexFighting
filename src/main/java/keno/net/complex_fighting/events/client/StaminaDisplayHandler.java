package keno.net.complex_fighting.events.client;

import com.mojang.blaze3d.systems.RenderSystem;
import keno.net.complex_fighting.ComplexFighting;
import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import keno.net.complex_fighting.components.cardinal.stamina.StaminaComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class StaminaDisplayHandler implements HudRenderCallback {
    // Stamina texture (Will make outline more bright tmrw)
    private final Identifier STAMINA_BORDER_TEXTURE = ComplexFighting.modLoc("textures/hud/stamina_bar_border.png");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MatrixStack stack = drawContext.getMatrices();
        Matrix4f positionMatrix = stack.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;


        RenderSystem.enableBlend();
        renderStaminaBar(buffer, tessellator, stack, positionMatrix, drawContext, player);
        RenderSystem.disableBlend();

        // drawContext.fill(100, 10, 330, 20, 0, color);
    }

    public void renderStaminaBar(BufferBuilder buffer, Tessellator tessellator, MatrixStack stack, Matrix4f positionMatrix, DrawContext context, ClientPlayerEntity player) {
        // Render the bar's border
        int brown = 0xff16110e;
        int scaledWidth = context.getScaledWindowWidth() / 2;
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        buffer.vertex(positionMatrix, scaledWidth - 120, 5, 0).color(brown).texture(0f, 0f).next();
        buffer.vertex(positionMatrix, scaledWidth - 120, 25, 0).color(brown).texture(0f, 1f).next();
        buffer.vertex(positionMatrix, scaledWidth + 120, 25, 0).color(brown).texture(1f, 1f).next();
        buffer.vertex(positionMatrix, scaledWidth + 120, 5, 0).color(brown).texture(1f, 0f).next();
        RenderSystem.setShader(GameRenderer::getPositionColorTexProgram);
        RenderSystem.setShaderTexture(0, STAMINA_BORDER_TEXTURE);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        tessellator.draw();

        // Render the bar itself
        int gold = 0xffffdf00;

        StaminaComponent stamina = CFCardiComponents.STAMINA.get(player);
        float scalar = (stamina.getStamina() / 2f) / 100f;
        stack.push();
        //Note to self: Find a way to stop scalar dragging during regain
        //Scales the bar depending on player's stamina
        stack.translate(((scaledWidth * 2f) / 2f) - 0.25f, 1f, 1f);
        stack.scale(scalar, 1f, 1f);
        stack.translate(-(((scaledWidth * 2f) / 2f) - 0.25f), 1f, 1f);
        //Draw the bar itself
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        buffer.vertex(stack.peek(), scaledWidth - 118, 5, 0).color(gold).next();
        buffer.vertex(stack.peek(), scaledWidth - 118, 21, 0).color(gold).next();
        buffer.vertex(stack.peek(), scaledWidth + 118, 21, 0).color(gold).next();
        buffer.vertex(stack.peek(), scaledWidth + 118, 5, 0).color(gold).next();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f,1f);
        tessellator.draw();
        stack.pop();
    }
}
