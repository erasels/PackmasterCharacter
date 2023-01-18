package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BaalorBlueprint extends AbstractContentCard {
    public final static String ID = makeID("BaalorBlueprint");

    public BaalorBlueprint() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
        cardsToPreview = new TheCozyChair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            this.addToBot(new EmptyDeckShuffleAction());
            this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractDungeon.player.drawPile.addToBottom(new TheCozyChair());
            }
        });
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}