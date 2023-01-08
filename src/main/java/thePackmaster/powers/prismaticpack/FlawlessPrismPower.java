package thePackmaster.powers.prismaticpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.prismaticpack.PrismaticUtil;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.List;

public class FlawlessPrismPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("FlawlessPrism");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FlawlessPrismPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        List<AbstractCard> cards = PrismaticUtil.getRandomDifferentColorCardInCombat(AbstractCard.CardType.POWER, null, this.amount);
        for (AbstractCard c : cards) {
            this.addToBot(new MakeTempCardInHandAction(c));
        }
    }

    @Override
    public void updateDescription() {
        this.description = this.amount == 1 ? DESCRIPTIONS[0].replace("{0}", this.amount + "") : DESCRIPTIONS[1].replace("{0}", this.amount + "");
    }
}
