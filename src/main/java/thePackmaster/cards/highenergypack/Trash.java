package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Trash extends AbstractPackmasterCard {
    public final static String ID = makeID("Trash");
    // intellij stuff skill, self, common, , , , , , 

    public Trash() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExhaustAction(1, false));
        atb(new GainEnergyAction(1));
    }

    public void upp() {
        selfRetain = true;
    }
}