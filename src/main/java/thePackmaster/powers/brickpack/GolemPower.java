package thePackmaster.powers.brickpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.adp;

public class GolemPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(GolemPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GolemPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        return info.type == DamageInfo.DamageType.NORMAL ? damageAmount - amount : damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
    }
}