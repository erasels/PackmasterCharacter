package thePackmaster.powers.brickpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.*;

public class SalvagePower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(SalvagePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SalvagePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.cost == -2)
            applyToSelf(new VigorPower(adp(), amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}