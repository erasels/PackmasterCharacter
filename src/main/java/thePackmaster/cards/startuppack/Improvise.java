package thePackmaster.cards.startuppack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Improvise extends AbstractStartUpCard {
    public final static String ID = makeID("Improvise");

    public Improvise() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = this.block = 5;
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int cardsDiscarded = -1;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.isInnate) {
                addToTop((AbstractGameAction) new DiscardSpecificCardAction(c));
                cardsDiscarded++;
            }
        }
        addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        addToBot(new DrawCardAction(cardsDiscarded));
    }

    @Override
    public void upp() {
        this.upgradeBlock(3);
    }
}