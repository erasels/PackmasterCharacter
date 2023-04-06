package thePackmaster.powers.darksoulspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GreenBlossomPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GreenBlossomPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public GreenBlossomPower(AbstractPlayer owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true, owner, amount);
        updateDescription();
    }

    public void atStartOfTurnPostDraw() {
        this.flashWithoutSound();
        Wiz.atb(new GainEnergyAction(1));
        Wiz.atb(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
