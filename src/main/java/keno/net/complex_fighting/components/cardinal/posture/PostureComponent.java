package keno.net.complex_fighting.components.cardinal.posture;

import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class PostureComponent implements PostureComponentInterface, AutoSyncedComponent, CommonTickingComponent {
    Entity entity;
    int posture;
    int postureCap;

    public PostureComponent(Entity provider, int posture) {
        this.entity = provider;
        this.posture = posture;
        this.postureCap = posture;
    }

    @Override
    public int getPosture() {
        return this.posture;
    }

    @Override
    public void lowerPosture(int posture) {
        this.posture = Math.clamp(this.posture - posture, 0, postureCap);
        CFCardiComponents.POSTURE.sync(entity);
    }

    @Override
    public void gainPosture(int posture) {
        this.posture = Math.clamp(this.posture + posture, 0, postureCap);
        CFCardiComponents.POSTURE.sync(entity);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.posture = tag.getInt("posture");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("posture", this.posture);
    }

    @Override
    public void tick() {
        if (this.entity instanceof LivingEntity living) {
            if (living.isBlocking()) {
                if (living.getLastAttackedTime() > 0) {
                    this.gainPosture(1);
                }
            }
            if (living instanceof PlayerEntity player) {
                if (this.getPosture() < 20) {
                    player.disableShield();
                }
            }
        }
    }
}
