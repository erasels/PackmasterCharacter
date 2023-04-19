package thePackmaster.powers.witchesstrikepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightFlightPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("MoonlightFlightPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public MoonlightFlightPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
    }
    public void onChannel(AbstractOrb orb) {
        addToBot(new ApplyPowerAction(owner,owner,new FocusPower(owner,amount)));
        addToBot(new ApplyPowerAction(owner,owner,new LoseFocusPower(owner,amount)));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
