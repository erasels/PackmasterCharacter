package thePackmaster.powers.intriguepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.intriguepack.AbstractIntrigueCard;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class PowerStrugglePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PowerStrugglePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public PowerStrugglePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.rarity == AbstractCard.CardRarity.RARE && usedCard.color != AbstractCard.CardColor.CURSE)
        {
            Wiz.atb(new GainEnergyAction(1));
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractIntrigueCard.demote(usedCard);
                    isDone = true;
                }
            });
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
