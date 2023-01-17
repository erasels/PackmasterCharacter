package thePackmaster.powers.strikepack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeOfGeniusUpgradedPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("StrikeOfGeniusUpgradedPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public StrikeOfGeniusUpgradedPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);

    }
    public void atStartOfTurn() {
        for (int i = 0; i < this.amount; ++i) {
            AbstractCard strike = Wiz.returnTrulyRandomPrediCardInCombat(card ->
                    card.hasTag(AbstractCard.CardTags.STRIKE) && card.rarity != AbstractCard.CardRarity.SPECIAL, true);
            strike.freeToPlayOnce = true;
            strike.upgrade();
            if (!strike.exhaust) CardModifierManager.addModifier(strike, new ExhaustMod());
            this.addToBot(new MakeTempCardInHandAction(strike));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}