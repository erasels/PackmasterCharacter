package thePackmaster.powers.strikepack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeABargainPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("StrikeABargainPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public StrikeABargainPower(AbstractCreature owner) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,0);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK) return super.canPlayCard(card);
        else return false;
    }

    @Override
    public void atEndOfRound() {
        removeThis();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
