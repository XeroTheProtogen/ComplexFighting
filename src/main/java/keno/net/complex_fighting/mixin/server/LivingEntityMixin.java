package keno.net.complex_fighting.mixin.server;

import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import keno.net.complex_fighting.components.cardinal.posture.PostureComponent;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damageShield(F)V", shift = At.Shift.AFTER))
    private void lowerPostureWhenBlocking(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((LivingEntity) (Object) this instanceof ServerPlayerEntity player) {
            PostureComponent posture = CFCardiComponents.POSTURE.get(player);
            posture.lowerPosture(20);
        }
    }
}
