package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BaalorBlueprint extends AbstractPackmasterCard {
    public final static String ID = makeID("BaalorBlueprint");
    // intellij stuff skill, self, special, , , , , , 

    public BaalorBlueprint() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new TheCozyChair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            this.addToBot(new EmptyDeckShuffleAction());
            this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }


    }

    public void upp() {
        upgradeBaseCost(1);
    }
}