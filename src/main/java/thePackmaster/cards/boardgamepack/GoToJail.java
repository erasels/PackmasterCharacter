package thePackmaster.cards.boardgamepack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import thePackmaster.powers.boardgamepack.JailPower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GoToJail extends AbstractBoardCard {
    public final static String ID = makeID(GoToJail.class.getSimpleName());

    private static ArrayList<TooltipInfo> ToolTip;

    public GoToJail() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        isChance = true;
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this, null, 0, true, true), true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new JailPower(p, 1), 1));
        if(upgraded)
        {
            int gold = 20;
            addToBot(new GainGoldAction(gold));
            for (int i = 0; i < gold; i++)
                AbstractDungeon.effectList.add(new GainPennyEffect(p, p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, true));
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        if(ToolTip == null)
        {
            ToolTip = new ArrayList<>();
            ToolTip.add(new TooltipInfo(BaseMod.getKeywordProper(BoardGameKeywordManager.CHANCE_ID), BaseMod.getKeywordDescription(BoardGameKeywordManager.CHANCE_ID)));
        }
        return ToolTip;
    }

    public void upp() {
        uDesc();
    }
}