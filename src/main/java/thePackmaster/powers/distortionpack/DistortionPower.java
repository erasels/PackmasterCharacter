package thePackmaster.powers.distortionpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import thePackmaster.patches.distortionpack.EnemyOnExhaustPatch;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.distortionpack.PixelEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DistortionPower extends AbstractPackmasterPower implements EnemyOnExhaustPatch.EnemyOnExhaustPower {
    public static final String POWER_ID = makeID("DistortionPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AbstractCreature source;
    private final ArrayList<AbstractGameEffect> powerEffects;

    public DistortionPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
        this.source = source;

        powerEffects = ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
    }

    @Override
    public void enemyOnExhaust(AbstractCard c) {
        if (this.amount > 0) {
            powerEffects.add(new GainPowerEffect(this));
            int amt = this.amount;
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!DistortionPower.this.owner.isDeadOrEscaped())
                        AbstractDungeon.effectList.add(new FlashPowerEffect(DistortionPower.this));
                    this.isDone = true;
                }
            });
            AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner, new DamageInfo(Wiz.p(), amt, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE, true));
            --this.amount;
            updateDescription();
        }

        if (this.amount <= 0) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 0));
        }
    }

    @Override
    public void update(int slot) {
        super.update(slot);

        if (powerEffects.size() < amount * 2) {
            powerEffects.add(new PixelEffect(true, 14 * Settings.scale, 4));
        }
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
    }

    public void updateDescription() {
        if (this.owner != null && this.owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
        else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
