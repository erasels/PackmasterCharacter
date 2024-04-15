package thePackmaster.powers.showmanpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.showmanpack.NowYouDont;
import thePackmaster.powers.AbstractPackmasterPower;

public class NowYouDontPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("NowYouDontPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractCard toGenerate;

    public NowYouDontPower(AbstractCreature owner, int amount, AbstractCard toMake) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        toGenerate = toMake;
    }

    @Override
    public void updateDescription() {
        if (toGenerate == null){
            toGenerate = new NowYouDont();
        }
        this.description = DESCRIPTIONS[0] + toGenerate.name + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (toGenerate == null){
            toGenerate = new NowYouDont();
        }
        this.addToBot(new MakeTempCardInHandAction(toGenerate.makeCopy()));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }
}
