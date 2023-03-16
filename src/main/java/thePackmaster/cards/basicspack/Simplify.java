package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.basicpack.DrawFilteredCardsAndUpgradeAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Simplify extends AbstractPackmasterCard {
    public final static String ID = makeID("Simplify");

    public Simplify() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawFilteredCardsAndUpgradeAction(1, (c) -> c.rarity == CardRarity.BASIC, true, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
