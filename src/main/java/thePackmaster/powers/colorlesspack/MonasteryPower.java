package thePackmaster.powers.colorlesspack;


import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.colorless.Enlightenment;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.makeInHand;

public class MonasteryPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("MonasteryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MonasteryPower() {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, -1);
        canGoNegative = false;
    }

    private int stage = 0;

    private static List<String> cards = Arrays.asList(
            Madness.ID,
            Purity.ID,
            Enlightenment.ID,
            Apotheosis.ID
    );

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        makeInHand(CardLibrary.getCopy(cards.get(stage)));
        stage++;
        if (stage >= cards.size()) {
            removeThis();
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0];
        }
        else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
