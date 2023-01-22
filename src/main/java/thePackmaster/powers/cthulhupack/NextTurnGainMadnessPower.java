package thePackmaster.powers.cthulhupack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NextTurnGainMadnessPower extends AbstractPackmasterPower implements InvisiblePower {
    public static final String POWER_ID = makeID("NextTurnGainMadnessPower");
    public static final String NAME = "";

    private boolean upgradeMadness;

    public NextTurnGainMadnessPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);

        upgradeMadness = upgraded;
    }

    public void atStartOfTurn() {
        AbstractCard c = new Madness();
        if (upgradeMadness) c.upgrade();

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, this.amount));
        addToBot(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, amount));
        removeThis();
    }


}
