package thePackmaster.powers.warriorpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.IncreaseDamageModifier;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

public class PolishingPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(PolishingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public PolishingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type.equals(AbstractCard.CardType.ATTACK) && usedCard.baseDamage > 0) {
            this.flash();
            for (AbstractCard c : GetAllInBattleInstances.get(usedCard.uuid)) {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        CardModifierManager.addModifier(c, new IncreaseDamageModifier(PolishingPower.this.amount));
                        isDone = true;
                    }
                });
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}