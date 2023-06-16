package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CloranthyRing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("CloranthyRing");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public CloranthyRing() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        this.exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainEnergyAction(Wiz.countDebuffs(p)));
        Wiz.atb(new DrawCardAction(this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}