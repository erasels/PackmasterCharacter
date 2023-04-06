package thePackmaster.powers.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.marisapack.AmplifyPowerHook;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ArcingEnergyPower extends AbstractPackmasterPower implements AmplifyPowerHook {
    public static final String POWER_ID = makeID("ArcingEnergyPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public int usesThisTurn;

    public ArcingEnergyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        usesThisTurn = 0;
    }

    @Override
    public void atStartOfTurn() {
        usesThisTurn = 0;
        updateDescription();
    }

    @Override
    public void onAmplify(AbstractCard c) {
        if (usesThisTurn < amount) {
            this.flash();
            atb(new GainEnergyAction(1));
            usesThisTurn++;
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder desc = new StringBuilder(DESCRIPTIONS[0]);
        if (amount > 1)
            desc.append(DESCRIPTIONS[2]).append(amount).append(DESCRIPTIONS[3]);
        else
            desc.append(DESCRIPTIONS[1]);
        desc.append(DESCRIPTIONS[4]);
        if (usesThisTurn == amount)
            desc.append(DESCRIPTIONS[8]);
        else if (usesThisTurn > 0) {
            desc.append(DESCRIPTIONS[5]).append(usesThisTurn);
            if (usesThisTurn == 1)
                desc.append(DESCRIPTIONS[6]);
            else
                desc.append(DESCRIPTIONS[7]);
        }
        description = desc.toString();
    }
}