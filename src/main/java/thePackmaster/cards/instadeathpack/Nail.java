package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.vfx.instadeathpack.QuickStakeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Nail extends AbstractInstadeathCard {
    public final static String ID = makeID("Nail");

    public Nail() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        this.magicNumber = this.baseMagicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new QuickStakeEffect(p, m), Settings.FAST_MODE ? 0.3f : 0.6f));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
    }

    public void triggerOnExhaust() {
        this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Precision(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}
