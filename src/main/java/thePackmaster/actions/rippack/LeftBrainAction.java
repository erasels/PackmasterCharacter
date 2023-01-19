package thePackmaster.actions.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.rippack.AbstractRippedArtCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelfTop;
import static thePackmaster.util.Wiz.att;

public class LeftBrainAction extends AbstractGameAction {
    @Override
    public void update() {
        int counter = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (isArtOrStatus(c)) {
                counter++;
                att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (isArtOrStatus(c)) {
                counter++;
                att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (isArtOrStatus(c)) {
                counter++;
                att(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
        }
        if(counter > 0) {
            applyToSelfTop(new StrengthPower(AbstractDungeon.player, counter));
            CardCrawlGame.sound.play(makeID("RipPack_Ahh"));
        }
        isDone = true;
    }

    private boolean isArtOrStatus(AbstractCard c) {
        return (c instanceof AbstractRippedArtCard || c.type == AbstractCard.CardType.STATUS);
    }
}
