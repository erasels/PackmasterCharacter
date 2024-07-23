package thePackmaster.powers.shamanpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.text.MessageFormat;

public class IgnitePower extends AbstractPackmasterPower implements HealthBarRenderPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("Ignite");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IgnitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF,false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!this.owner.isPlayer && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            playApplyPowerSfx();
            addToBot(new DamageAction(this.owner, new DamageInfo(Wiz.p(), amount, DamageInfo.DamageType.HP_LOSS)));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (owner.isPlayer) {
            flashWithoutSound();
            addToBot(new DamageAction(this.owner, new DamageInfo(Wiz.p(), amount, DamageInfo.DamageType.HP_LOSS)));
        }
    }

    @Override
    public void updateDescription() {
        description = MessageFormat.format(this.owner.isPlayer ? DESCRIPTIONS[0] : DESCRIPTIONS[1], this.amount);
    }

    @Override
    public int getHealthBarAmount() {
        return owner.isPlayer ? 0 : this.amount;
    }

    @Override
    public Color getColor() {
        return new Color(-5963521);
    }
}
