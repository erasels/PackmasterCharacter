package thePackmaster.powers.secretlevelpack;


import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class AchievementHunterPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("AchievementHunterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AchievementHunterPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        canGoNegative = false;
    }

    @Override
    public void onSpecificTrigger() {
        flash();
        atb(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1), 1));
        if (amount > 0) {
            amount--;
            if (amount == 0) {
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
                        att(new ApplyPowerAction(owner, owner, new DexterityPower(owner, 2), 2));
                    }
                });
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount > 0) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
        else {
            description = DESCRIPTIONS[2];
        }
    }
}
