package thePackmaster.powers.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.marisapack.AmplifyPowerHook;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class RecallPower extends AbstractPackmasterPower implements AmplifyPowerHook {
    public static final String POWER_ID = makeID("RecallPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RecallPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 0);
    }

    @Override
    public void onAmplify(AbstractCard c) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                flash();
                this.isDone = true;
            }
        });
        atb(new WaitAction(0.1F));
        atb(new ExhumeAction(false));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}