package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StardustReveriePower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(StardustReveriePower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean triggered = false;

    public StardustReveriePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this));
        if (!triggered) {
            Wiz.applyToSelf(new ChargeUpPower(amount));
            Wiz.atb(new DrawCardAction(1));
            flash();

            action.exhaustCard = true;
            triggered = true;
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new StardustReveriePower(amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}




