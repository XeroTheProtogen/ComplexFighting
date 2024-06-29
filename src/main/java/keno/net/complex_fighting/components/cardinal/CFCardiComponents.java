package keno.net.complex_fighting.components.cardinal;

import keno.net.complex_fighting.ComplexFighting;
import keno.net.complex_fighting.components.cardinal.posture.PostureComponent;
import keno.net.complex_fighting.components.cardinal.stamina.StaminaComponent;
import net.minecraft.entity.Entity;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.internal.base.ComponentRegistrationInitializer;

public final class CFCardiComponents implements EntityComponentInitializer, ComponentRegistrationInitializer {
    public static final ComponentKey<StaminaComponent> STAMINA = registerCardinalComponent("stamina", StaminaComponent.class);
    public static final ComponentKey<PostureComponent> POSTURE = registerCardinalComponent("posture", PostureComponent.class);

    private static <T extends Component> ComponentKey<T> registerCardinalComponent(String componentName, Class<T> component) {
        return ComponentRegistry.getOrCreate(ComplexFighting.modLoc(componentName), component);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        readPostureData(registry);
        registry.registerForPlayers(POSTURE, player -> new PostureComponent(player, 100), RespawnCopyStrategy.NEVER_COPY);
        registry.registerForPlayers(STAMINA, StaminaComponent::new, RespawnCopyStrategy.NEVER_COPY);
    }

    private void readPostureData(EntityComponentFactoryRegistry registry) {
        for (Class<? extends Entity> entity : ComplexFighting.POSTURE_DATA.keySet()) {
            int posture = ComplexFighting.POSTURE_DATA.get(entity);
            registry.registerFor(entity, POSTURE, entry -> new PostureComponent(entry, posture));
            ComplexFighting.LOGGER.info("{} has been registered with a posture of {}", entity.getName(), posture);
        }
    }
}
