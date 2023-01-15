package thePackmaster.powers.conjurerpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.actions.bitingcoldpack.FrostbiteDamageAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.relics.bitingcoldpack.Snowglobe;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PetraPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PetraPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final int EFFECT = 35;

    public PetraPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        Wiz.reducePower(this);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner != this.owner) {
            int amt = getBlockAmount(damageAmount);
            addToBot(new ApplyPowerAction(info.owner, info.owner, new NextTurnBlockPower(info.owner, amt), amt));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + EFFECT + DESCRIPTIONS[1];
    }

    public int getBlockAmount(int damage)
    {
        return Math.max(1, (int) (damage * EFFECT / 100f));
    }
}
