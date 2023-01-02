package thePackmaster.powers.intothebreachpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class StormGenPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("StormGenPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public StormGenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        ArrayList<AbstractMonster> withDebuff = new ArrayList<>();

        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters)
            for (AbstractPower p : m.powers)
                if (p.type == PowerType.DEBUFF && !withDebuff.contains(m)) {
                    withDebuff.add(m);
                    break;
                }

        this.flash();
        for (int i = 0; i < withDebuff.size()*this.amount; i++)
            att(new ChannelAction(new Lightning()));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}