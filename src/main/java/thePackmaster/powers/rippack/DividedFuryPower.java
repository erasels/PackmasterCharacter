package thePackmaster.powers.rippack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DividedFuryPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DividedFuryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DividedFuryPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
        description = amount > 1 ? description + DESCRIPTIONS[2] : description + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    int validCards = countValidCardsInHandToMakeRippable(); //Get count of valid cards
                    //Set the amount of cards to make rippable to be either the power amount or the number of valid cards, whichever is less
                    int cardsToMakeRippable = validCards < DividedFuryPower.this.amount ? validCards : DividedFuryPower.this.amount;
                    for (int i = 0; i < cardsToMakeRippable; i++) {
                        flash();
                        AbstractCard card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                        while(!cardValidToMakeRippable(card)) { //Get random cards until you get one you can make rippable
                            card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                        }
                        CardModifierManager.addModifier(card, new RippableModifier(false));
                        card.flash();
                    }
                    isDone = true;
                }
            });
        }
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
    }
}
