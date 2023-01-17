package thePackmaster.powers.rippack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.cards.rippack.FuryAttack;
import thePackmaster.cards.rippack.FurySkill;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DividedFuryPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DividedFuryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DividedFuryPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount;
        description = amount > 1 ? description + DESCRIPTIONS[2] : description + DESCRIPTIONS[1];
    }

    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (int i = 0; i < amount; i++) {
                AbstractCard card = AbstractDungeon.cardRandomRng.randomBoolean() ? new FuryAttack() : new FurySkill();
                addToBot(new MakeTempCardInHandAction(card));
            }
        }
    }

    public void stackPower(int stackAmount) {
        fontScale = 8.0F;
        amount += stackAmount;
    }
}
