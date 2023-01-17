package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

public class MidnightOil extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("MidnightOil");
    private static final int COST = 0;

    public MidnightOil() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            this.addToBot(new ExhaustAction(1, false));
        } else {
            this.addToBot(new ExhaustAction(1, true, false, false));
        }
        Wiz.atb(new GainEnergyAction(1));
    }
}
