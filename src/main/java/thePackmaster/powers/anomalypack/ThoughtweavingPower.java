package thePackmaster.powers.anomalypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.ThoughtweavingAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.util.Wiz.att;

public class ThoughtweavingPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ThoughtweavingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger("Packmaster");

    public ThoughtweavingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    public void onUseCard(AbstractCard tmpCard, UseCardAction action) {
        //logger.info("Check 0");
        if (tmpCard instanceof AbstractPackmasterCard) {
            //logger.info("Check 1");
            AbstractPackmasterCard card = (AbstractPackmasterCard) tmpCard;
            if (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL) {
                this.flash();
                if (AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
                    AbstractDungeon.player.getPower(NoDrawPower.POWER_ID).flash();
                } else {
                    AbstractCard.CardType typeToFind;

                    AbstractCardPack packToAvoid = card.getParent();

                    if (card.type == AbstractCard.CardType.ATTACK) {
                        typeToFind = AbstractCard.CardType.SKILL;
                    } else {
                        typeToFind = AbstractCard.CardType.ATTACK;
                    }

                    Wiz.atb(new ThoughtweavingAction(this.amount, typeToFind, packToAvoid));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] +  DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0] + (amount) + DESCRIPTIONS[2];
        }
    }

}