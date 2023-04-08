package thePackmaster.powers.summonerspellspack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.actions.bitingcoldpack.FrostbiteDamageAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.relics.bitingcoldpack.Snowglobe;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class GrievousWoundsPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GrievousWoundsPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GrievousWoundsPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, -1);
    }

    @Override
    public int onHeal(int healAmount) {
        this.flash();
        this.addToBot(new LoseHPAction(owner, owner, healAmount));
        return 0;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
