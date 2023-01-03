package thePackmaster.powers.distortionpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        this.source = source;

        powerEffects = ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
    }

    @Override
    public void enemyOnExhaust(AbstractCard c) {
        powerEffects.add(new GainPowerEffect(this));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.effectList.add(new FlashPowerEffect(DistortionPower.this));
                this.isDone = true;
            }
        });
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
