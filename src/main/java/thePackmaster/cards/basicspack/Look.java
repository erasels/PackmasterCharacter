package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.basics.DrawFilteredCardsAndUpgradeAction;
import thePackmaster.actions.transmutationpack.DrawFilteredCardsAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Look extends AbstractPackmasterCard {
    public final static String ID = makeID("Look");

    public Look() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawFilteredCardsAndUpgradeAction(magicNumber, (c) -> c.rarity == CardRarity.BASIC, true, 1));
        addToBot(new GainEnergyAction(secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
