package thePackmaster.powers.transmutationpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.StrengthPotion;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.HashMap;

public class CrystalResonancePower extends AbstractPackmasterPower implements TransmutableAffectingPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CrystalResonancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrystalResonancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onTransmute(HashMap<AbstractCard, AbstractCard> pairs) {
        if (!pairs.isEmpty()) {
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        }
    }
}
