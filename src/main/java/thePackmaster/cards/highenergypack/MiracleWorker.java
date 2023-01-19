package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.topDeck;

public class MiracleWorker extends AbstractHighEnergyCard {
    public final static String ID = makeID("MiracleWorker");
    // intellij stuff skill, self, common, , , 9, 3, , 

    public MiracleWorker() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 9;
        cardsToPreview = new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        topDeck(new Miracle());
    }

    public void upp() {
        upgradeBlock(3);
    }
}