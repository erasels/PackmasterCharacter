package thePackmaster.powers.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

public class ReleaseValvePower extends AbstractPackmasterPower implements FlowAffectingPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ReleaseValvePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final AbstractCreature source;

    public ReleaseValvePower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.source = source;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onFlow(ArrayList<AbstractCard> amountDiscarded) {
        for (int i = 0; i < amountDiscarded.size(); ++i) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(owner, new DamageInfo(source, amount, DamageInfo.DamageType.THORNS)));
        }
    }
}
