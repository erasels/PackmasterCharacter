package thePackmaster.powers.anomalypack;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.ThoughtweavingAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

public class ThoughtweavingPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ThoughtweavingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger("Packmaster");

    public ThoughtweavingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        String parentID = SpireAnniversary5Mod.cardParentMap.getOrDefault(card.cardID, null);
        if (parentID != null) {
            if (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL) {
                this.flash();
                if (AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
                    AbstractDungeon.player.getPower(NoDrawPower.POWER_ID).flash();
                } else {
                    AbstractCard.CardType typeToFind;
                    if (card.type == AbstractCard.CardType.ATTACK) {
                        typeToFind = AbstractCard.CardType.SKILL;
                    } else {
                        typeToFind = AbstractCard.CardType.ATTACK;
                    }

                    Wiz.atb(new ThoughtweavingAction(this.amount, typeToFind, parentID));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] +  DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0] + (amount) + DESCRIPTIONS[2];
        }
    }

}