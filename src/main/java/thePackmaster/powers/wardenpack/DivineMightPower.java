package thePackmaster.powers.wardenpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DivineMightPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DivineMightPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DivineMightPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);

        if (this.amount > 1)
        {
            CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
            this.addToBot(new VFXAction(this.owner, new InflameEffect(this.owner), 0.1F));
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.YELLOW, true));

            this.addToBot(new GainEnergyAction(2));
            this.addToBot(new DrawCardAction(2));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 10), 10));
            this.addToBot(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, 10), 10));

            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
