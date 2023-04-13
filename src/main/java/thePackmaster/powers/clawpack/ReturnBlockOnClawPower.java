package thePackmaster.powers.clawpack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReturnBlockOnClawPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ReturnBlockOnClawPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ReturnBlockOnClawPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.DEBUFF,false,owner,amount);

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && card.hasTag(CLAW) && (owner == action.target || (boolean) ReflectionHacks.getPrivate(card, AbstractCard.class, "isMultiDamage"))) {
            this.flash();
            this.addToTop(new GainBlockAction(AbstractDungeon.player, this.amount, Settings.FAST_MODE));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
