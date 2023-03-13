package thePackmaster.powers.jockeypack;


import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class DerbyPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DerbyPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DerbyPower() {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() >= 6) {
            flash();
            applyToSelf(new StrengthPower(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]);
        sb.append(DESCRIPTIONS[2] + AbstractDungeon.actionManager.cardsPlayedThisTurn.size() + (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1 ? DESCRIPTIONS[3] : DESCRIPTIONS[4]));
        description = sb.toString();
    }
}
