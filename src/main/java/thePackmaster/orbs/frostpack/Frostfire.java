
package thePackmaster.orbs.frostpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BetterFireballEffect;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Frostfire extends Frost {
    private static OrbStrings orbString;

    private float particleTimer = 0.0F;
    public Frostfire() {
        super();
        this.ID = makeID("Frostfire");
        this.name = orbString.NAME;
        updateDescription();
    }

    public void onEvoke() {
        super.onEvoke();
        AbstractMonster m = Wiz.getRandomEnemy();
        applyToEnemy(m, new IgnitePower(m, passiveAmount));
        Wiz.vfx(new MissileStrikeEffect(m.hb.cX, m.hb.cY, randomFlareColor()), Settings.ACTION_DUR_FASTER);
    }

    public void updateAnimation() {
        super.updateAnimation();

        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
                AbstractDungeon.effectList.add(new GhostlyWeakFireEffect(this.cX, this.cY));
            this.particleTimer = 0.06F;
        }
    }

    public void onEndOfTurn() {
        super.onEndOfTurn();
        AbstractMonster m = Wiz.getRandomEnemy();
        applyToEnemy(m, new IgnitePower(m, passiveAmount));
        Wiz.vfx(new MissileStrikeEffect(m.hb.cX, m.hb.cY, randomFlareColor()), Settings.ACTION_DUR_FASTER);

    }


    public static Color randomFlareColor()
    {
        int i = MathUtils.random(3);
        switch (i) {
            case 0:
                return Color.CYAN;
            case 1:
                return Color.BLUE;
            default:
                return Color.SKY;
        }
    }

    public AbstractOrb makeCopy() {
        return new Frostfire();
    }

    @Override
    public void updateDescription() {

        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] +
                this.passiveAmount + orbString.DESCRIPTION[1] +
                this.passiveAmount + orbString.DESCRIPTION[2] +
                this.evokeAmount + orbString.DESCRIPTION[1] +
                this.evokeAmount + orbString.DESCRIPTION[3];

    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("Frostfire");
    }
}
