package thePackmaster.cards.boardgamepack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.DelayedDiceRollAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.boardgamepack.OneTimeAdvantagePower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class D20 extends AbstractBoardCard {
    public final static String ID = makeID(D20.class.getSimpleName());

    private static ArrayList<TooltipInfo> ToolTip;
    static int DICE = 20;

    public D20() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DicePower(p, DICE), DICE));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        if(ToolTip == null)
        {
            ToolTip = new ArrayList<>();
            ToolTip.add(new TooltipInfo(BaseMod.getKeywordProper(BoardGameKeywordManager.DICE_ID), BaseMod.getKeywordDescription(BoardGameKeywordManager.DICE_ID)));
        }
        return ToolTip;
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}