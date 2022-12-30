package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.PackmasterModalChoiceCard;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DestinyDraw extends AbstractPackmasterCard {
    public final static String ID = makeID("DestinyDraw");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DestinyDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCardPack> choices = SpireAnniversary5Mod.getRandomPacks(false, 3);

        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (AbstractCardPack pack:choices
             ) {
            packCards.add(new PackmasterModalChoiceCard(pack.previewPackCard.cardID, pack.previewPackCard.name, pack.previewPackCard.rawDescription, true, () -> action(pack)));
        }

        addToBot(new EasyModalChoiceAction(packCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    public void action(AbstractCardPack pack) {
        AbstractCard c = SpireAnniversary5Mod.getRandomCardFromPack(pack).makeStatEquivalentCopy();
        c.setCostForTurn(0);

        addToBot(new MakeTempCardInHandAction(c));

    }
}