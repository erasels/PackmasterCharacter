package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.PackmasterModalChoiceCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

// This card used to be in the Core Set pack but no longer is
// However, we've kept it around since we may want to give it to the player from a Packmaster-specific event
// (with an appropriate cost, like an unremovable curse)
public class PackRip extends AbstractPackmasterCard {
    public final static String ID = makeID("PackRip");

    public PackRip() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCardPack> choices = SpireAnniversary5Mod.getRandomPacks(false, 3);

        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (AbstractCardPack pack:choices) {
            packCards.add(new PackmasterModalChoiceCard(pack.previewPackCard.cardID, pack.previewPackCard.name, pack.previewPackCard.rawDescription, true, () -> action(pack)));
        }

        addToBot(new EasyModalChoiceAction(packCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }

    public void action(AbstractCardPack pack) {
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, Wiz.hand().size(), true, false));
        for (AbstractCard c : pack.cards) {
            if(c.rarity == CardRarity.COMMON || c.rarity == CardRarity.UNCOMMON || c.rarity == CardRarity.RARE) {
               // if (upgraded) c.upgrade();
                addToBot(new MakeTempCardInHandAction(c));
            }
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}