package thePackmaster.powers.brickpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class JengaPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(JengaPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public JengaPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }


    @Override
    public void atStartOfTurn() {
        int count = 0;
        CardGroup pile = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard c : adp().hand.group) {
            if (c.cost == -2) {
                count++;
                pile.addToTop(c);
            }
        }

        for (AbstractCard c : adp().drawPile.group) {
            if (c.cost == -2) {
                count++;
                pile.addToTop(c);
            }
        }

        for (AbstractCard c : adp().discardPile.group) {
            if (c.cost == -2) {
                count++;
                pile.addToTop(c);
            }
        }

        boolean die = false;

        for (int i = 0; i < amount; i++) {
            if (pile.size() == 0) {
                die = true;
                continue;
            }

            int x = AbstractDungeon.cardRandomRng.random(0, pile.size() - 1);
            AbstractCard cardToExhaust = pile.group.get(x);

            // The action will check where the card is so I don't bother doing if statements here
            atb(new ExhaustSpecificCardAction(cardToExhaust, adp().hand));
            atb(new ExhaustSpecificCardAction(cardToExhaust, adp().drawPile));
            atb(new ExhaustSpecificCardAction(cardToExhaust, adp().discardPile));

            pile.removeCard(cardToExhaust);
        }

        if (die) {
            flash();
            addToBot(new VFXAction(new LightningEffect(owner.hb.cX, owner.hb.cY)));
            addToBot(new LoseHPAction(owner, owner, 99999));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}