package thePackmaster.powers.dragonwrathpack;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.dragonwrathpack.SmiteAction;
import thePackmaster.orbs.dragonwrathpack.LightOrb;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.witchesstrikepack.LoseFocusPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.addPotions;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class JudgementDayPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = makeID("JudgementDay");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public JudgementDayPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.loadRegion("end_turn_death");

        updateDescription();
    }

    public void onChannel(AbstractOrb orb) {
        if (orb instanceof Lightning){
            Wiz.applyToSelf(new confessionpower(owner,amount));
        }
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLD.cpy());
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new confessionpower(owner, amount);
    }
}

