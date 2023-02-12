
package thePackmaster.stances.serpentinepack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class VenemousAura extends StanceAuraEffect {

    public VenemousAura() {
        super("Wrath");
        //1.00000, 0.70196, 1.00000
        this.color = new Color(0.0f, MathUtils.random(0.5F, 0.7F), 0.2f, 0.0F);
    }

}