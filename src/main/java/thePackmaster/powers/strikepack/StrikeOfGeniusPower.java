package thePackmaster.powers.strikepack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import thePackmaster.actions.strikepack.StrikeOfGeniusAction;
import thePackmaster.cards.Strike;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeOfGeniusPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("StrikeOfGeniusPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public StrikeOfGeniusPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);

    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {

        //A little odd of a setup, but mirrors how Wallop operates.
        if (amount > 0) {
            Wiz.atb(new StrikeOfGeniusAction(target));
            amount--;
        }

        //amount change is happening instantly in the event of many attacks on the stack simultaneously.
        if (amount <= 0) {
            removeThis();
        }
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}