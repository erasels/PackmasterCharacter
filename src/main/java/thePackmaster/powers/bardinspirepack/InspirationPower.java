package thePackmaster.powers.bardinspirepack;


import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class InspirationPower extends AbstractPackmasterPower implements NonStackablePower, CloneablePowerInterface
{
    public static final String POWER_ID = makeID("Inspiration");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public InspirationPower(AbstractCreature owner, int cards, int percent)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, cards);
        isTwoAmount = true;
        priority = 7;
        amount2 = percent;
        updateDescription();
    }

    @Override
    public void updateDescription()
    {
        if (Settings.language == Settings.GameLanguage.ZHS) {
            name = amount2 + "级" + NAME;
        } else {
            name = NAME + " " + amount2;
        }
        if (amount == 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + DESCRIPTIONS[4] + amount2 + DESCRIPTIONS[5];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[3] + DESCRIPTIONS[4] + amount2 + DESCRIPTIONS[5];
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (isDamageDealingCard(card) || isBlockGainingCard(card)) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type)
    {
        // Only the first Inspiration alters the damage
        // It collects all the bonuses from another Inspiration and applies them all at once
        if (this == owner.getPower(ID) && type == DamageInfo.DamageType.NORMAL) {
            float additiveDamage = 0;
            for (AbstractPower p : owner.powers) {
                if (p instanceof InspirationPower) {
                    additiveDamage += damage * (((InspirationPower) p).amount2 / 100.0f);
                }
            }
            return damage + additiveDamage;
        }
        return damage;
    }

    @Override
    public float modifyBlock(float blockAmount)
    {
        // Only the first Inspiration alters the block
        // It collects all the bonuses from another Inspiration and applies them all at once
        if (this == owner.getPower(ID)) {
            float additiveBlock = 0;
            for (AbstractPower p : owner.powers) {
                if (p instanceof InspirationPower) {
                    additiveBlock += blockAmount * (((InspirationPower) p).amount2 / 100.0f);
                }
            }
            return blockAmount + additiveBlock;
        }
        return blockAmount;
    }

    @Override
    public boolean isStackable(AbstractPower power)
    {
        if (power instanceof InspirationPower) {
            return amount2 == ((InspirationPower) power).amount2;
        }
        return false;
    }

    @Override
    public void stackPower(int stackAmount)
    {
        super.stackPower(stackAmount);
    }

    @Override
    public AbstractPower makeCopy()
    {
        return new InspirationPower(owner, amount, amount2);
    }

    private static boolean isDamageDealingCard(AbstractCard card)
    {
        return card.baseDamage >= 0;
    }

    private static boolean isBlockGainingCard(AbstractCard card)
    {
        return card.baseBlock >= 0;
    }
}
