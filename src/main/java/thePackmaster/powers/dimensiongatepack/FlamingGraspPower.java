package thePackmaster.powers.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.dimensiongatepack2.FlamePillar;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlamingGraspPower extends AbstractPackmasterPower implements InvisiblePower {
    public static final String POWER_ID = makeID("FlamingGraspPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public FlamingGraspPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, amount);

    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; i++) {
            addToBot(new MakeTempCardInHandAction(new FlamePillar()));
        }
        removeThisInvisibly();
    }

}
