package thePackmaster.powers.colorlesspack;


import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.makeInHand;

public class MonasteryPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("MonasteryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MonasteryPower() {
        super(POWER_ID, NAME, PowerType.BUFF, true, AbstractDungeon.player, 3);
        canGoNegative = false;
    }

    private static List<String> cards = Arrays.asList(
            Madness.ID,
            Purity.ID,
            Apotheosis.ID
    );

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        makeInHand(CardLibrary.getCopy(cards.get(3 - amount)));
        amount--;
        if (amount == 0) {
            removeThis();
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        if (amount == 1) {
            sb.append(DESCRIPTIONS[1]);
        } else {
            sb.append(DESCRIPTIONS[2]);
        }
        sb.append(DESCRIPTIONS[3]);
        for (int i = 0; i <= 3 - amount; i++) {
            sb.append(FontHelper.colorString(CardLibrary.getCard(cards.get(i)).originalName, "y"));
            if (i < 3 - amount) {
                sb.append(DESCRIPTIONS[4]);
            } else {
                sb.append(DESCRIPTIONS[5]);
            }
        }
        sb.append(DESCRIPTIONS[6]);
        description = sb.toString();
    }
}
