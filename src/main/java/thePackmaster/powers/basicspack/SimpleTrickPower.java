package thePackmaster.powers.basicspack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.Cardistry;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SimpleTrickPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(SimpleTrickPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SimpleTrickPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if (card.cardID.equals(Cardistry.ID) ) {
            flash();
            addToBot(new GainEnergyAction(1));
            this.amount--;
            this.updateDescription();
            if(this.amount == 0){
                addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            }
        }
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];

    }

}
