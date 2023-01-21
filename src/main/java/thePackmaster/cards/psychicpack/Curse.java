package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.psychicpack.CurseEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Curse extends LockingCard {
    public final static String ID = makeID("Curse");

    public Curse() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);

        if (m != null) {
            this.addToBot(new VFXAction(new CurseEffect(m), Settings.FAST_MODE ? 0.4f : 0.8f));
            this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        }
    }

    public void upp() {
        selfRetain = true;
    }
}