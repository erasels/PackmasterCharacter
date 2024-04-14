package thePackmaster.powers.rimworldpack;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.TheBombPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class BurningPassionPower extends AbstractPackmasterPower implements CloneablePowerInterface, BetterOnApplyPowerPower, OnReceivePowerPower {
    public static final String POWER_ID = makeID(BurningPassionPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    boolean doubledOnReceivePower, doubledOnReceivePowerStacks, postStartDraw;

    public BurningPassionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        doubledOnReceivePower = false;
        doubledOnReceivePowerStacks = false;
        postStartDraw = true;
    }

    @Override
    public void atStartOfTurn() {
        doubledOnReceivePower = false;
        doubledOnReceivePowerStacks = false;
        postStartDraw = false;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if(adp() != null && adp().hasPower(BurningPassionPower.POWER_ID))
                            ((BurningPassionPower)adp().getPower(BurningPassionPower.POWER_ID)).postStartDraw = true;
                        isDone = true;
                    }
                });
                isDone = true;
            }
        });
    }

    @Override
    public void updateDescription()
    {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new BurningPassionPower(owner, amount);
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power.amount != 0 && target == owner && power.type == PowerType.BUFF &&
            !doubledOnReceivePower && postStartDraw && !isTheBomb(power))
        {
            flash();
            doubledOnReceivePower = true;
            power.amount *= amount;
            power.updateDescription();
        }
        for (AbstractCard card: AbstractDungeon.player.hand.group)
            card.applyPowers();
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if(power.amount != 0 && target == owner && power.type == PowerType.BUFF &&
                !doubledOnReceivePowerStacks && postStartDraw && !isTheBomb(power))
        {
            doubledOnReceivePowerStacks = true;
            flash();
            return stackAmount * amount;
        }
        return stackAmount;
    }

    private boolean isTheBomb(AbstractPower power) {
        // We specifically exclude The Bomb, because its amount is the number of turns until it triggers,
        // which means that doubling it is generally a bad thing (unlike all other buff amount). It's also a
        // negative play experience to use up your Burning Passion stacks (a powerful and important ability)
        // on something like this (especially since many players won't even realize that The Bomb's amount
        // works like this).
        // There's no built-in way to double The Bomb's damage, and while we could write the necessary patches
        // to enable that, that doesn't seem like a good idea -- especially when the simple and intuitive solution
        // of just having Burning Passion ignore The Bomb is available.
        // We have to check for the ID starting with The Bomb's power ID because it adds a numeric offset to each
        // instance in order to make them not stack with each other.
        return power.ID.startsWith(TheBombPower.POWER_ID);
    }
}
