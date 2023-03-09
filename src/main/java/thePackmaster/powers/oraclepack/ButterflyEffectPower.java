package thePackmaster.powers.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ButterflyEffectPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("ButterflyEffectPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ButterflyEffectPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            AbstractGameAction drawAction = new AbstractGameAction() {
                @Override
                public void update() {
                    if (Wiz.drawPile().size() <= amount) {
                        while (Wiz.drawPile().size() > 0) {
                            Wiz.drawPile().moveToHand(Wiz.drawPile().getTopCard());
                        }
                    } else {
                        int cardsMoved = 0;
                        while (cardsMoved < amount) {
                            AbstractCard c = Wiz.drawPile().getRandomCard(AbstractDungeon.cardRandomRng);
                            Wiz.hand().moveToHand(c,Wiz.drawPile());
                            cardsMoved ++;
                        }
                    }
                    isDone = true;
                }
            };
            drawAction.amount = amount;
            addToBot(drawAction);
        }
    }

    public void updateDescription() {
        if (amount < 2) {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
