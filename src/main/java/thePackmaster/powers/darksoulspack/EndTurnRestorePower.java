package thePackmaster.powers.darksoulspack;

import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EndTurnRestorePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("EndTurnRestorePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public EndTurnRestorePower(AbstractCreature owner, int amount){
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        this.loadRegion("regen");
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.flashWithoutSound();
        Wiz.att(new RegenAction(owner, amount));
        removeThis();
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
