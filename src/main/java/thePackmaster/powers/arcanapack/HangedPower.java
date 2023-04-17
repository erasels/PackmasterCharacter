package thePackmaster.powers.arcanapack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import thePackmaster.actions.arcanapack.AllEnemyLoseHPAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.vfx.arcanapack.TargetedFlashPowerEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HangedPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("HangedPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final ArrayList<AbstractGameEffect> powerEffects;

    public HangedPower(final AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        powerEffects = ReflectionHacks.getPrivate(this, AbstractPower.class, "effect");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        this.powerEffects.add(new GainPowerEffect(this));
        AbstractDungeon.actionManager.addToTop(new AllEnemyLoseHPAction(this.owner, this.amount).enemyEffect(
                (m)->AbstractDungeon.effectList.add(new TargetedFlashPowerEffect(this, m))
        ));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}