package keno.net.complex_fighting.components.cardinal.stamina;

import keno.net.complex_fighting.components.cardinal.CFCardiComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

public class StaminaComponent implements StaminaComponentInterface, AutoSyncedComponent, CommonTickingComponent {
    private int stamina = 200;
    private final int STAMINA_CAP = 200;
    private final int STAMINA_BASE = 0;
    private final PlayerEntity provider;

    public StaminaComponent(PlayerEntity provider) {
        this.provider = provider;
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }

    @Override
    public void setStamina(int value) {
        this.stamina = Math.clamp(this.getStamina() + value, STAMINA_BASE, STAMINA_CAP);
        CFCardiComponents.STAMINA.sync(this.provider);
    }

    @Override
    public void decrementStamina() {
        if (this.getStamina() > STAMINA_BASE) {
            this.stamina--;
            CFCardiComponents.STAMINA.sync(this.provider);
        }
    }

    @Override
    public void incrementStamina() {
        if (this.getStamina() < STAMINA_CAP) {
            this.stamina++;
            CFCardiComponents.STAMINA.sync(this.provider);
        }
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.stamina = tag.getInt("stamina");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("stamina", this.stamina);
    }

    @Override
    public void tick() {
        if (!this.provider.isInCreativeMode()) {
            if (this.provider.isSprinting() && this.getStamina() > STAMINA_BASE) {
                decrementStamina();
            } else {
                incrementStamina();
            }
        }
    }
}
