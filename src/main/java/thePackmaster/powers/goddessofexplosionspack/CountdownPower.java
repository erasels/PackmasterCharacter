package thePackmaster.powers.goddessofexplosionspack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.goddessofexplosionspack.AtomicPiledriver;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class CountdownPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("CountdownPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final boolean upgraded;
    private boolean triggered = false;

    public CountdownPower(AbstractCreature owner, int amount, boolean upgrade) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        upgraded = upgrade;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (amount > 1)
            reducePower(1);
        else if (!this.triggered){
            AbstractCard ap = new AtomicPiledriver();

            if (upgraded)
                ap.upgrade();

            Wiz.att(new GainEnergyAction(2));
            Wiz.att(new MakeTempCardInHandAction(ap, 1, false));
            Wiz.att(new RemoveSpecificPowerAction(owner, owner, this));
            this.triggered = true;
        }
    }

    @Override
    public void updateDescription() {
        if (upgraded)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}