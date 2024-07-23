package thePackmaster.powers.rimworldpack;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HoldCardPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID(HoldCardPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static int stasisIdOffset = 0;

    private AbstractCard card;
    //Copy of the Stasis power from the automaton minions, but nonstackable
    public HoldCardPower(AbstractCreature owner, AbstractCard card) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, -1);
        this.card = card;
        updateDescription();
    }

    @Override
    public void onDeath() {
        if (AbstractDungeon.player.hand.size() != BaseMod.MAX_HAND_SIZE)
            addToBot(new MakeTempCardInHandAction(card, false, true));
        else
            addToBot(new MakeTempCardInDiscardAction(card, true));
    }

    @Override
    public void updateDescription() {
        if(card != null && card.name != null)
            description = DESCRIPTIONS[0] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[1];
    }

}
