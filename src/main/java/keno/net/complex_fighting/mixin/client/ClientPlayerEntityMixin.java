package keno.net.complex_fighting.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(value = ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @ModifyReturnValue(method = "canSprint", at = @At("RETURN"))
    private boolean hasStamina(boolean original) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
        return original && CFCardiComponents.STAMINA.get(player).getStamina() > 0;
    }
}
