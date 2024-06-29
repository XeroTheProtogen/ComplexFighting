package keno.net.complex_fighting.components.cardinal.posture;

import org.ladysnake.cca.api.v3.component.Component;

public interface PostureComponentInterface extends Component {
    int getPosture();
    void lowerPosture(int posture);
    void gainPosture(int posture);
}
