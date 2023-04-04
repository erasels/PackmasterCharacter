package thePackmaster.cards.instadeathpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.ScryCallbackPatch;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.vfx.instadeathpack.ColoredThrowDaggerEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Needle extends AbstractInstadeathCard {
    public final static String ID = makeID("Needle");

    private static final int PRECISION = 3;

    public Needle() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new ColoredThrowDaggerEffect(m.hb.cX, m.hb.cY, Color.CYAN)));
        }
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        addToBot(ScryCallbackPatch.scryWithCallback(magicNumber, (cards)->{
            int amt = 0;
            for (AbstractCard c : cards) {
                if (c.type == CardType.SKILL) {
                    amt += PRECISION;
                }
            }
            if (amt > 0) {
                addToTop(new ApplyPowerAction(p, p, new Precision(p, amt), amt));
            }
        }));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}
