package thePackmaster.powers.strikepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeDummyJrPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("StrikeDummyJrPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public StrikeDummyJrPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);

    }


    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return card.hasTag(AbstractCard.CardTags.STRIKE) ? super.atDamageGive(damage, type, card) + amount : super.atDamageGive(damage, type, card);
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
