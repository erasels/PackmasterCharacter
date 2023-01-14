package thePackmaster.cards.utilitypack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class Excitement extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Excitement");
    private static final int COST = 0;
    private static final int ENERGY = 2;
    private static final int EXHAUSTIVE = 2;

    public Excitement() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
        ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        this.addToBot(new GainEnergyAction(ENERGY));
    }
}
