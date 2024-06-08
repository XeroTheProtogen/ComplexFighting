package keno.net.complex_fighting.components.cardinal.stamina;

import org.ladysnake.cca.api.v3.component.Component;

public interface StaminaComponentInterface extends Component {
    int getStamina();
    void setStamina(int value);
    void decrementStamina();
    void incrementStamina();
}
