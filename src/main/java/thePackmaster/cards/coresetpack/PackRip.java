package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.PackmasterModalChoiceCard;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PackRip extends AbstractPackmasterCard {
    public final static String ID = makeID("PackRip");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public PackRip() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
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

    public void action(AbstractCardPack pack) {

        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 10, true, false));

        for (AbstractCard c:pack.cards
             ) {
            if (upgraded) c.upgrade();
            addToBot(new MakeTempCardInHandAction(c));
        }
    }

    public void upp() {
    }
}