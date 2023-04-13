package thePackmaster.powers.replicatorspack;


import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.UUID;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class IterativeDesignPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("IterativeDesignPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static UUID lastCardPlayed = new UUID(1, 1);


    public IterativeDesignPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, true, owner, amount);

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractCard masterdeckCard = StSLib.getMasterDeckEquivalent(card);
        if (masterdeckCard == null || (card.isInAutoplay && card.purgeOnUse && card.uuid.equals(lastCardPlayed))) {
            if (Settings.FAST_MODE) {
                addToBot(new GainBlockAction(Wiz.adp(), Wiz.adp(), this.amount, true));
            } else {
                addToBot(new GainBlockAction(Wiz.adp(), Wiz.adp(), this.amount));
            }
            flash();
        }
        lastCardPlayed = card.uuid;
    }

    @Override
    public AbstractPower makeCopy() {
        return new IterativeDesignPower(this.owner, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}




