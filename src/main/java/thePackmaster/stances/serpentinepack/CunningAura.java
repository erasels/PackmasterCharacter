package thePackmaster.stances.serpentinepack;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;

public class CunningAura extends StanceAuraEffect {

    public CunningAura() {
        super("Wrath");
        //1.00000, 0.70196, 1.00000
        this.color = new Color(MathUtils.random(0.2F, 0.3F),0f, MathUtils.random(0.4F, 0.6F), 0.0F);
    }

}
