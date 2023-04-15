package thePackmaster.powers.dimensiongate2pack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.dimensiongatepack.ScrollOfIntellect;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TomeOfIntellectPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("TomeOfIntellectPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public TomeOfIntellectPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        Wiz.atb(new MakeTempCardInDrawPileAction(new ScrollOfIntellect(), amount, true, true));
    }

    @Override
    public void updateDescription() {
        if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
