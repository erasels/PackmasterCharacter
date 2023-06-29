package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DestinyDraw extends AbstractPackmasterCard {
    public final static String ID = makeID("DestinyDraw");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DestinyDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCardPack> choices = SpireAnniversary5Mod.getRandomPacks(p.chosenClass == ThePackmaster.Enums.THE_PACKMASTER, 3);

        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (AbstractCardPack pack : choices) {
            AbstractCard c = SpireAnniversary5Mod.getRandomCardFromPack(pack).makeStatEquivalentCopy();
            packCards.add(c);
        }

        addToBot(new FlexibleDiscoveryAction(packCards, true));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}