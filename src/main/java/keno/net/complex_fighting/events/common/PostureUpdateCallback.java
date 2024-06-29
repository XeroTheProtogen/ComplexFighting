package keno.net.complex_fighting.events.common;

import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import keno.net.complex_fighting.components.cardinal.posture.PostureComponent;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PostureUpdateCallback implements AttackEntityCallback {
    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!player.isSpectator() && entity instanceof LivingEntity living) {
            PostureComponent posture = CFCardiComponents.POSTURE.get(living);
            if (living.isBlocking()) {
                posture.lowerPosture(20);
            }
            if (posture.getPosture() < 20) {
                entity.kill();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
