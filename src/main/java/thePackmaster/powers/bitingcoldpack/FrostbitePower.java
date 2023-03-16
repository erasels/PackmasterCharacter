package thePackmaster.powers.bitingcoldpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.actions.bitingcoldpack.FrostbiteDamageAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.relics.bitingcoldpack.Snowglobe;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FrostbitePower extends AbstractPackmasterPower implements HealthBarRenderPower {
    public static final String POWER_ID = makeID("FrostbitePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public FrostbitePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            atb(new FrostbiteDamageAction(this.owner, this));
            atb(new WaitAction(0.01F));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            // Application of Frostbite
            // includes Snowglobe check
            AbstractRelic s = AbstractDungeon.player.getRelic(Snowglobe.ID);
            if (s != null) {
                atb(new ApplyPowerAction(this.owner, info.owner, new FrostbitePower(owner, 2)));
                atb(new RelicAboveCreatureAction(this.owner, s));
                s.flash();
            } else {
                atb(new ApplyPowerAction(this.owner, info.owner, new FrostbitePower(owner, 1)));
            }
        }
        return damageAmount;
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.05F);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return CardHelper.getColor(35.0f, 245.0f, 245.0f);
    }
}
