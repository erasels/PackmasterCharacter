package thePackmaster.powers.downfallpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.cards.downfallpack.SlimeCrush;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NextTurnGainSlimeCrushPower extends AbstractPackmasterPower implements InvisiblePower {
    public static final String POWER_ID = makeID("NextTurnGainSlimeCrushPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;

    public NextTurnGainSlimeCrushPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);

    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.getCard(SlimeCrush.ID).makeCopy(), this.amount));
        removeThisInvisibly();
    }


}
