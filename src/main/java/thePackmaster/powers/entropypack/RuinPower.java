package thePackmaster.powers.entropypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RuinPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("RuinPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RuinPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.amount > 0 && damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            this.addToTop(new LoseHPAction(this.owner, info.owner == null ? this.owner : info.owner, this.amount));

            this.flash();
            if (amount >= 8) {
                float adjust = Math.max(-0.5f, -0.2f - ((amount - 8) * 0.01f));

                this.addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        CardCrawlGame.sound.playAV("ATTACK_PIERCING_WAIL", adjust, 0.5f);
                        this.isDone = true;
                    }
                });
            }

            this.amount = 0;
        }

        return damageAmount;
    }

    public void updateDescription() {
        if (this.owner != null && this.owner.isPlayer) {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
        else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}