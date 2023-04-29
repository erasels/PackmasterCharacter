package thePackmaster.powers.instadeathpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.IncreaseDamageModifier;
import thePackmaster.powers.AbstractPackmasterPower;

public class Precision extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("Precision");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public Precision(final AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + this.amount : damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            IncreaseDamageModifier mod = new IncreaseDamageModifier(this.amount);
            this.addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    CardModifierManager.addModifier(card, mod);
                }
            });
            this.addToTop(new ReducePowerAction(this.owner, this.owner, this, this.amount));
        }

    }// 51

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}