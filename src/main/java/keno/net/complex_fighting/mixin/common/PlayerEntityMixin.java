package keno.net.complex_fighting.mixin.common;

import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import keno.net.complex_fighting.components.cardinal.stamina.StaminaComponent;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    public void giveStamina(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.getItem() == null) return;
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (stack.get(DataComponentTypes.FOOD) != null) {
            StaminaComponent stamina = CFCardiComponents.STAMINA.get(player);
            if (stack.getItem() == Items.GOLDEN_APPLE) {
                stamina.setStamina(200);
            } else {
                stamina.setStamina(stamina.getStamina() + 50);
            }
        }
    }
}
