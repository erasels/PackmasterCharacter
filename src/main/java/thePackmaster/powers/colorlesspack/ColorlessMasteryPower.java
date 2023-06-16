package thePackmaster.powers.colorlesspack;


import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ColorlessMasteryPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ColorlessMasteryPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ColorlessMasteryPower() {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.color == AbstractCard.CardColor.COLORLESS) {
            flash();
            applyToSelf(new StrengthPower(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
