package thePackmaster.powers.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.boardgamepack.AbstractBoardCard;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Random;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class DicePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(DicePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public boolean sound;

    public DicePower(AbstractCreature owner, int amount, boolean sound) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.sound = sound;
        this.amount = amount;
        updateDescription();
    }

    public DicePower(AbstractCreature owner, int sides) {
        this(owner, sides, true);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action)
    {
        if (card.baseBlock >= 0 || card.type == AbstractCard.CardType.ATTACK)
        {
            flash();
            if(card instanceof AbstractBoardCard)
            {
                if(!((AbstractBoardCard) card).reroll)
                    addToBot(new ReducePowerAction(owner, owner, ID, amount));
            }
            else
                addToBot(new ReducePowerAction(owner, owner, ID, amount));
        }
    }

    @Override
    public float modifyBlock(float blockAmount)
    {
        if(blockAmount < 0)
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
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
