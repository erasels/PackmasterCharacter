package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class CloranthyRing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("CloranthyRing");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public CloranthyRing() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));

        for (int i = 0; i < Wiz.countDebuffs(p); i++)
            this.addToBot(new GainEnergyAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}