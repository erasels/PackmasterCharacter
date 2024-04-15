package thePackmaster.cards.showmanpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SmokeAndMirrors extends AbstractShowmanCard {
    public final static String ID = makeID("SmokeAndMirrors");

    public SmokeAndMirrors() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i<25; i++) {
            addToBot(new VFXAction(new ExhaustBlurEffect(p.hb.cX + Settings.scale * ((float) Math.random() * 160f) - 80f, p.hb.cY + Settings.scale * ((float) Math.random() * 160f) - 80f)));
        }
        blck();
    }

    public void triggerExhaustIncreaseBlock() {
        flash(Color.BLUE.cpy());
        baseBlock += magicNumber;
        initializeDescription();
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}