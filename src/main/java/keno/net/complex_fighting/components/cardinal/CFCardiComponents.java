package keno.net.complex_fighting.components.cardinal;

import keno.net.complex_fighting.ComplexFighting;
import keno.net.complex_fighting.components.cardinal.stamina.StaminaComponent;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import org.ladysnake.cca.internal.base.ComponentRegistrationInitializer;

public final class CFCardiComponents implements EntityComponentInitializer, ComponentRegistrationInitializer {
    public static final ComponentKey<StaminaComponent> STAMINA = registerTickingCardinalComponent("stamina", StaminaComponent.class);

    private static <T extends Component> ComponentKey<T> registerTickingCardinalComponent(String componentName, Class<T> component) {
        return ComponentRegistry.getOrCreate(ComplexFighting.modLoc(componentName), component);
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(STAMINA, StaminaComponent::new, RespawnCopyStrategy.NEVER_COPY);
    }
}
