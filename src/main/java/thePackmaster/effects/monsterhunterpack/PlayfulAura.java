package thePackmaster.effects.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class PlayfulAura extends StanceAuraEffect {

    public PlayfulAura() {
        super("Calm");
        //1.00000, 0.70196, 1.00000
        this.color = new Color(MathUtils.random(0.90F, 1F), MathUtils.random(0.65F, 0.75F), 1.0F, 0.0F);
    }

}
