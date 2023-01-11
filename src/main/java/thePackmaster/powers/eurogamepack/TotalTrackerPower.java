package thePackmaster.powers.eurogamepack;


import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class TotalTrackerPower extends AbstractPackmasterPower implements InvisiblePower {
    public static final String POWER_ID = makeID("TotalTrackerPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public TotalTrackerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, amount);

    }
    //This is a tracker for MilitaryExpansion, it has no functionality and is invisible

    @Override
    public void updateDescription() {description = DESCRIPTIONS[0];}
}
