package thePackmaster.powers.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.boardgamepack.AbstractBoardCard;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class DicePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(DicePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public List<Integer> dice = new ArrayList<>();

    public DicePower(AbstractCreature owner, int sides) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, sides);
        dice.add(sides);
        amount =  roll(sides);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (card.baseBlock >= 0 || card.type == AbstractCard.CardType.ATTACK)
        {
            flash();
            addToBot(new ReducePowerAction(owner, owner, ID, amount));
            if(card instanceof AbstractBoardCard && ((AbstractBoardCard) card).reroll)
            {
                for (int diceSize: dice) {
                    addToBot(new ApplyPowerAction(owner, owner, new DicePower(owner, diceSize), diceSize));
                }
            }
        }
    }

    @Override
    public float modifyBlock(float blockAmount)
    {
        if(blockAmount < 1)
            return blockAmount;
        return Math.max(blockAmount + amount, 0);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + this.amount;
        return damage;
    }

    @Override
    public void playApplyPowerSfx() {
        int rand = new Random().nextInt(3) + 1;
        addToTop(new SFXAction(modID + "dice" + rand));
    }

    @Override
    public void stackPower(int amount) {
        dice.add(amount);
        this.amount += roll(amount);
    }

    private int roll(int sides) {
        int curRoll = 0;
        int advantage = getAdvantage();
        for(int roll = 0; roll <= advantage; roll++)
        {
            int newRoll = AbstractDungeon.cardRandomRng.random(sides - 1) + 1;
            //ADD VFX HERE
            if(newRoll > curRoll)
                curRoll = newRoll;
        }
        return curRoll;
    }

    private int getAdvantage() {
        //ADD ADVANTAGE CHECKS HERE
        int adv = 0;
        if(owner.hasPower(OneTimeAdvantagePower.POWER_ID))
        {
            adv += owner.getPower(OneTimeAdvantagePower.POWER_ID).amount;
            addToTop(new RemoveSpecificPowerAction(owner, owner, owner.getPower(OneTimeAdvantagePower.POWER_ID)));
        }
        if(owner.hasPower(AdvantagePower.POWER_ID))
            adv += owner.getPower(AdvantagePower.POWER_ID).amount;
        return adv;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
