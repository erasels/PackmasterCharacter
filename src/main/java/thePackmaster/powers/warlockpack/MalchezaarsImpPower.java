package thePackmaster.powers.warlockpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.warlockpack.Imp;
import thePackmaster.powers.AbstractPackmasterPower;

public class MalchezaarsImpPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(MalchezaarsImpPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MalchezaarsImpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = (this.amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1]).replace("{0}", this.amount + "");
    }

    @Override
    public void onExhaust(AbstractCard c) {
        if (!(c instanceof Imp)) {
            this.flash();
            this.addToBot(new MakeTempCardInDrawPileAction(new Imp(), this.amount, true, true));
        }

    }
}